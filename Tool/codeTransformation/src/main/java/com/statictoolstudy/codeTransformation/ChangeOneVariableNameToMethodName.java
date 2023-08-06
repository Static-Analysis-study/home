package com.statictoolstudy.code_transformation;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.visitor.ModifierVisitor;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.visitor.Visitable;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ParseProblemException;

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
 * This class is used to change one variable name to a method name
 *
    */

public class ChangeOneVariableNameToMethodName {

    private static class ChangeOneVariableNameToMethodNameVisitor extends ModifierVisitor<Void> {
        private List<String> methodNames = new ArrayList<>();
        private List<String> variables = new ArrayList<>();
        private Random random = new Random();

        @Override
        public Visitable visit(MethodDeclaration methodDeclaration, Void arg) {
            // if the method name is "main", do not add it to the list
            if (!methodDeclaration.getNameAsString().equals("main")){
                methodNames.add(methodDeclaration.getNameAsString());
            }
            return super.visit(methodDeclaration, arg);
        }

        @Override
        public Visitable visit(VariableDeclarator variableDeclarator, Void arg) {
            // if the variable id a field, do not add it to the list
            // if (variableDeclarator.getParentNode().get().getParentNode().get().getClass().equals(FieldDeclaration.class)){
            if (!variableDeclarator.getNameAsString().equals("args") 
            && !variableDeclarator.getNameAsString().equals("out") 
            && !variableDeclarator.getNameAsString().equals("in") 
            && !variableDeclarator.getNameAsString().equals("err")
            && !variableDeclarator.getNameAsString().contains("ignore")){
                variables.add(variableDeclarator.getNameAsString());
            }
            // }
            return super.visit(variableDeclarator, arg);
        }

        @Override
        public Visitable visit(Parameter parameter,Void arg){
            if (!parameter.getNameAsString().equals("args")){
                variables.add(parameter.getNameAsString());
            }
            return super.visit(parameter, arg);
        }

        public void processChanges(CompilationUnit cu) {
            if (!methodNames.isEmpty() && !variables.isEmpty()) {
                
                String randomMethodName = methodNames.get(random.nextInt(methodNames.size()));
                String randomVariable = variables.get(random.nextInt(variables.size()));

                // change the name of the variable to the name of the method
                ModifierTheNameVisitor modifierTheNameVisitor = new ModifierTheNameVisitor();
                modifierTheNameVisitor.VariableName = randomVariable;
                modifierTheNameVisitor.methodName = randomMethodName;
                modifierTheNameVisitor.visit(cu, null);
            }
        }
    }

    private static class ModifierTheNameVisitor extends ModifierVisitor<Void> {

        // change the Name of the NameExpr to the given name
        private String VariableName;
        private String methodName;

        @Override
        public Visitable visit(NameExpr nameExpr, Void arg) {
            String name = nameExpr.getNameAsString();
            if (name.equals(VariableName)) {
                nameExpr.setName(methodName);
            }
            return super.visit(nameExpr, arg);
        }

        @Override
        public Visitable visit(VariableDeclarator variableDeclarator, Void arg) {
            String name = variableDeclarator.getNameAsString();
            if (name.toString().equals(VariableName)) {
                SimpleName value = StaticJavaParser.parseSimpleName(methodName);
                variableDeclarator.setName(value); 
            }
            return super.visit(variableDeclarator, arg);
        }

        @Override
        public Visitable visit(MethodCallExpr methodCallExpr, Void arg) {
            SimpleName name = (SimpleName) methodCallExpr.getName().accept(this, arg);
            Expression scope = methodCallExpr.getScope().map(s -> (Expression) s.accept(this, arg)).orElse(null);
            if (scope != null){
                if (scope.toString().equals(VariableName)) {
                    Expression value = StaticJavaParser.parseExpression(methodName);
                    methodCallExpr.setScope(value); 
                }
                if (scope.toString().contains(VariableName)) {
                    Expression value = StaticJavaParser.parseExpression(scope.toString().replace(VariableName, methodName));
                    methodCallExpr.setScope(value); 
                }
            }
            return super.visit(methodCallExpr, arg); 
        }

        @Override
        public Visitable visit(Parameter parameter, Void arg) {
            String name = parameter.getNameAsString();
            if (name.toString().equals(VariableName)) {
                SimpleName value = StaticJavaParser.parseSimpleName(methodName);
                parameter.setName(value); 
            }
            return super.visit(parameter, arg);
        }

        @Override
        public Visitable visit(FieldAccessExpr fieldAccessExpr, Void arg) {
            String name = fieldAccessExpr.getNameAsString();
            if (name.toString().equals(VariableName)) {
                SimpleName value = StaticJavaParser.parseSimpleName(methodName);
                fieldAccessExpr.setName(value); 
            }
            return super.visit(fieldAccessExpr, arg);
        }


    }

    public static void ParseCodeFile(int level, String path, File file) {

        try {
            
            CompilationUnit cu = StaticJavaParser.parse(file);

            ChangeOneVariableNameToMethodNameVisitor visitor = new ChangeOneVariableNameToMethodNameVisitor();
            visitor.visit(cu, null);
            visitor.processChanges(cu);

            System.out.println("Modified file: " + cu.toString());

            writeFileToDict(file,cu.toString());

        } catch (IOException e) {
            System.err.println("Caught an IOException: " + e.getMessage());
            e.printStackTrace();
        } catch (ParseProblemException e) {
                System.err.println("Parsing error in file: " + file + " - " + e.getMessage());
        } catch (IndexOutOfBoundsException e){
            System.err.println("IndexOutOfBoundsException in file: " + file + " - " + e.getMessage());
        }

    }

    public static void writeFileToDict(File file, String content){

        String new_filepath= "";
        new_filepath = file.toString().replace("originalCase","someIdentifierCase");
        FileWriter.writeContent(content,new_filepath);
        System.out.println("[INFO]"+new_filepath+ "write down!");
        
    }

    
}

