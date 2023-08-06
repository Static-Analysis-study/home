package com.statictoolstudy.code_transformation;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.ModifierVisitor;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.visitor.Visitable;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ParseProblemException;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.stmt.ExpressionStmt;

import com.github.javaparser.ast.*;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.modules.*;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.type.*;
import com.github.javaparser.utils.Pair;

import com.statictoolstudy.code_transformation.fileProcess.DirExplorer;
import com.statictoolstudy.code_transformation.fileProcess.FileWriter;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Optional;

/**
 * This is to move a method to a nested class.
 *
 */

public class MoveMethodToNestedClass {

    public static void ParseCodeFile(int level, String path, File file) {

        try {
            
            CompilationUnit cu = StaticJavaParser.parse(file);

            // get ClassOrInterfaceDeclaration
            Optional<ClassOrInterfaceDeclaration> classDeclarationOptional = cu.getClassByName(cu.getTypes().get(0).getNameAsString());
            ClassOrInterfaceDeclaration classDeclaration;
            boolean isUtilityClass = false;
            if (classDeclarationOptional.isPresent()) {
                classDeclaration = classDeclarationOptional.get();
                if(isUtilityClass(classDeclaration)) return;
                System.out.println("ClassOrInterfaceDeclaration exists: " + classDeclaration.getNameAsString());
            } else {
                System.out.println("ClassOrInterfaceDeclaration not found for: " + cu.getTypes().get(0).getNameAsString());
                return;
            }

            // get Class name
            String className = classDeclaration.getNameAsString();
            // get all method name
            MethodNameCollector methodNameCollector = new MethodNameCollector();
            methodNameCollector.visit(cu, null);
            List<String> methodNames = methodNameCollector.getMethodNames();
            methodNames = methodNames.stream()
                .filter(name -> !name.contains("main") && !name.contains("run") && !name.contains("test") && !name.contains("start"))
                .collect(Collectors.toList());
            
            if (methodNames == null || methodNames.isEmpty()) {
                System.out.println("No method in code!");
            }else{
                Random rand = new Random();
                int index = rand.nextInt(methodNames.size());
                String methodname =  methodNames.get(index);

                MoveMethodVisitor visitor = new MoveMethodVisitor(methodname ,className);
                visitor.visit(cu, null);
                // Output Java code
                System.out.println(cu.toString());
                writeFileToDict(file,cu.toString());
            }
        } catch (IOException e) {
            System.err.println("Caught an IOException: " + e.getMessage());
            e.printStackTrace();
        } catch (ParseProblemException e) {
                System.err.println("Parsing error in file: " + file + " - " + e.getMessage());
        } catch (IndexOutOfBoundsException e){
            System.err.println("IndexOutOfBoundsException in file: " + file + " - " + e.getMessage());
        }

    }

    private static class MoveMethodVisitor extends ModifierVisitor<Void> {
        private final String methodName;
        private final String className;
        private boolean methodMoved = false;
        private boolean isStaticMethod = false;
        
        public MoveMethodVisitor(String methodName, String className) {
            this.methodName = methodName;
            this.className = className;
        }

        @Override
        public Visitable visit(MethodDeclaration methodDeclaration, Void arg) {
            
            if (!methodMoved && methodDeclaration.getNameAsString().equals(methodName)) {
                isStaticMethod = methodDeclaration.isStatic();
                // 获取方法所在的类
                ClassOrInterfaceDeclaration parentClass = methodDeclaration.findAncestor(ClassOrInterfaceDeclaration.class).orElse(null);
                if (parentClass != null) {
                    // Create nested class
                    ClassOrInterfaceDeclaration nestedClass = new ClassOrInterfaceDeclaration();
                    nestedClass.setName("NestedClass");
                    parentClass.addMember(nestedClass);

                    // if isStaticMethod is true ，add static to nestedClass
                    if (isStaticMethod){
                        nestedClass.setStatic(true);
                    }

                    // copy method and remove original method
                    MethodDeclaration copiedMethod = methodDeclaration.clone();
                    methodDeclaration.remove();
                    nestedClass.addMember(copiedMethod);

                    methodMoved = true;
                }
            }

            return super.visit(methodDeclaration, arg);
        }

        @Override
        public Visitable visit(MethodCallExpr methodCallExpr, Void arg) {
            
            if (methodCallExpr.getNameAsString().equals(methodName) && isStaticMethod) {
                // when calling a static method, we need to use the class name to call the method
                FieldAccessExpr fieldAccessExpr = new FieldAccessExpr(new NameExpr(className), "NestedClass");
                methodCallExpr.setScope(fieldAccessExpr);

            } else if(methodCallExpr.getNameAsString().equals(methodName) && !isStaticMethod) {
                MethodDeclaration parentMethod = methodCallExpr.findAncestor(MethodDeclaration.class).orElse(null);
                if (parentMethod != null && parentMethod.isStatic()) {

                    System.out.println("parentMethod is static");

                    // create outer class instance
                    VariableDeclarationExpr outerInstanceDeclaration = new VariableDeclarationExpr(
                            new VariableDeclarator(new ClassOrInterfaceType(className), "outerInstance",
                                    new ObjectCreationExpr(null, new ClassOrInterfaceType(className), new NodeList<>())));
                    ExpressionStmt outerInstanceStmt = new ExpressionStmt(outerInstanceDeclaration);

                    // NestedClass nestedInstance = outerInstance.new NestedClass();
                    VariableDeclarationExpr nestedInstanceDeclaration = new VariableDeclarationExpr(
                            new VariableDeclarator(new ClassOrInterfaceType("NestedClass"), "nestedInstance",
                                    new ObjectCreationExpr(new NameExpr("outerInstance"), new ClassOrInterfaceType("NestedClass"),new NodeList<>())));
                    ExpressionStmt nestedInstanceStmt = new ExpressionStmt(nestedInstanceDeclaration);

                    // call method
                    ExpressionStmt methodStmt = new ExpressionStmt(
                            new MethodCallExpr(new NameExpr("nestedInstance"), methodCallExpr.getNameAsString(), NodeList.nodeList()));
                    
                    methodCallExpr.remove();
                    BlockStmt blockStmt = new BlockStmt(NodeList.nodeList(outerInstanceStmt, nestedInstanceStmt, methodStmt));

                    // replace method call with block statement
                    methodCallExpr.getParentNode().ifPresent(parent -> {
                        if (parent instanceof ExpressionStmt) {
                            parent.replace(blockStmt);
                        } else if (parent instanceof BlockStmt) {
                            ((BlockStmt) parent).addStatement(blockStmt);
                        }
                    });

                }else if(parentMethod != null && !parentMethod.isStatic()){

                    // For non-static method, we need to use "this" to call the method
                    // OuterClass.InnerClass innerObj = this.new InnerClass();
                    System.out.println("parentMethod is not static");

                    // 创建静态嵌套类实例
                    VariableDeclarationExpr nestedInstanceDeclaration = new VariableDeclarationExpr(
                        new VariableDeclarator(
                            new ClassOrInterfaceType("NestedClass"), "nestedInstance",
                            new ObjectCreationExpr(null, new ClassOrInterfaceType("NestedClass"), new NodeList<>())
                        )
                    );
                    ExpressionStmt nestedInstanceStmt = new ExpressionStmt(nestedInstanceDeclaration);

                    // call method
                    ExpressionStmt methodStmt = new ExpressionStmt(
                        new MethodCallExpr(new NameExpr("nestedInstance"), methodCallExpr.getNameAsString(), NodeList.nodeList())
                    );

                    methodCallExpr.remove();
                    BlockStmt blockStmt = new BlockStmt(NodeList.nodeList(nestedInstanceStmt, methodStmt));

                    // replace method call with block statement
                    methodCallExpr.getParentNode().ifPresent(parent -> {
                        if (parent instanceof ExpressionStmt) {
                            parent.replace(blockStmt);
                        } else if (parent instanceof BlockStmt) {
                            ((BlockStmt) parent).addStatement(blockStmt);
                        }
                    }); 
                }
            }

            return super.visit(methodCallExpr, arg);
        }
    }

    // get all method name
    private static class MethodNameCollector extends VoidVisitorAdapter<Void> {
        private final List<String> methodNames = new ArrayList<>();

        public List<String> getMethodNames() {
            return methodNames;
        }

        @Override
        public void visit(MethodDeclaration methodDeclaration, Void arg) {
            // add method name to list
            methodNames.add(methodDeclaration.getNameAsString());
            super.visit(methodDeclaration, arg);
        }
    }

    // check if class is utility class, we donot change the code if it is utility class
    private static boolean isUtilityClass(ClassOrInterfaceDeclaration classDeclaration) {
        if (!classDeclaration.isFinal()) {
            return false;
        }

        boolean hasPrivateConstructor = false;
        for (ConstructorDeclaration constructor : classDeclaration.getConstructors()) {
            if (constructor.getModifiers().stream().anyMatch(modifier -> modifier.getKeyword() == Modifier.Keyword.PRIVATE)) {
                hasPrivateConstructor = true;
            } else {
                return false;
            }
        }

        for (MethodDeclaration method : classDeclaration.getMethods()) {
            if (!method.getModifiers().stream().anyMatch(modifier -> modifier.getKeyword() == Modifier.Keyword.STATIC)) {
                return false;
            }
        }

        for (FieldDeclaration field : classDeclaration.getFields()) {
            if (!field.getModifiers().stream().anyMatch(modifier -> modifier.getKeyword() == Modifier.Keyword.STATIC)) {
                System.out.println("returnn false");
                return false;
            }
        }
        return hasPrivateConstructor;
    }

    public static void writeFileToDict(File file, String content){

        String new_filepath= "";
        new_filepath = file.toString().replace("originalCase","nestedclassCase");
        FileWriter.writeContent(content,new_filepath);
        System.out.println("[INFO]"+new_filepath+ " write down!");
        
    }

    
}
