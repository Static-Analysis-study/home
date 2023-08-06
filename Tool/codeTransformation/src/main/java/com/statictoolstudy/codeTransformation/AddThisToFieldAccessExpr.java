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
 * This is aim to add this to the field access
 */

public class AddThisToFieldAccessExpr {


    private static class AddThisToFieldDeclarationVisitor extends VoidVisitorAdapter<Void> {

        public static boolean modifyFlag = false;

        @Override
        public void visit(FieldDeclaration fieldDeclaration, Void arg) {
            if (!fieldDeclaration.isStatic()) {
                fieldDeclaration.getVariables().forEach(variable -> {
                    String fieldName = variable.getNameAsString();
                    fieldDeclaration.findAncestor(ClassOrInterfaceDeclaration.class).ifPresent(classOrInterfaceDeclaration ->
                            classOrInterfaceDeclaration.accept(new ModifierVisitor<Void>() {
                                @Override
                                public Visitable visit(FieldAccessExpr fieldAccessExpr, Void arg) {
                                    if (fieldAccessExpr.getNameAsString().equals(fieldName)) {
                                        
                                        fieldAccessExpr.findAncestor(MethodDeclaration.class).ifPresent(methodDeclaration -> {
                                            if (!methodDeclaration.isStatic()) {
                                                Expression scope = fieldAccessExpr.getScope();
                                                SimpleName name = fieldAccessExpr.getName();

                                                if (scope == null) {
                                                    fieldAccessExpr.setScope(new ThisExpr());
                                                    modifyFlag = true;
                                                }else{
                                                    
                                                    String scopeStr = scope.toString();
                                                    if( !scopeStr.contains("this") && !scopeStr.contains(".") && !scopeStr.contains("super") && !scopeStr.contains("new")){
                                                        Optional<MethodDeclaration> methodName = fieldAccessExpr.findAncestor(MethodDeclaration.class);
                                                        List<FieldDeclaration> fieldDeclarationList = methodName.get().findAll(FieldDeclaration.class);
                                                        List<VariableDeclarationExpr> variableDeclarationList = methodName.get().findAll(VariableDeclarationExpr.class);
                                                        // find the variable of method
                                                        List<Parameter> parameterList = methodName.get().getParameters();
                                                        // find if the scope is in fieldDeclarationList or variableDeclarationList or parameterList
                                                        boolean flag = false;
                                                        for( FieldDeclaration fieldDeclaration : fieldDeclarationList ){
                                                            if( fieldDeclaration.getVariable(0).getNameAsString().equals(scopeStr) ){
                                                                flag = true;
                                                                break;
                                                            }
                                                        }
                                                        for( VariableDeclarationExpr variableDeclaration : variableDeclarationList ){
                                                            if( variableDeclaration.getVariable(0).getNameAsString().equals(scopeStr) ){
                                                                flag = true;
                                                                break;
                                                            }
                                                        }
                                                        for( Parameter parameter : parameterList ){
                                                            if( parameter.getNameAsString().equals(scopeStr) ){
                                                                flag = true;
                                                                break;
                                                            }
                                                        }
                                                        if( flag == false){
                                                            Expression thisExpr = new ThisExpr();
                                                            Expression enclosedExpr = new FieldAccessExpr(thisExpr, scope.toString());
                                                            fieldAccessExpr.setScope(enclosedExpr);
                                                            modifyFlag = true;
                                                            
                                                        }

                                                    }
                                                }

                                            }
                                        });

                                    }
                                    return super.visit(fieldAccessExpr, arg);
                                }
                            }, arg)
                    );
                });
            }
        }
    }


    public static void ParseCodeFile(int level, String path, File file) {

        try {
            
            CompilationUnit cu = StaticJavaParser.parse(file);
            System.out.println("[Info] file"+file);
            
            // Find all FieldDeclarations and add "this." to their method calls.
            AddThisToFieldDeclarationVisitor visitor = new AddThisToFieldDeclarationVisitor();
            AddThisToFieldDeclarationVisitor.modifyFlag = false;
            visitor.visit(cu, null); 

            if( AddThisToFieldDeclarationVisitor.modifyFlag == true ){
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

    public static void writeFileToDict(File file, String content){

        String new_filepath= "";
        new_filepath = file.toString().replace("originalCase","addThisCase");
        FileWriter.writeContent(content,new_filepath);
        System.out.println("[INFO]"+new_filepath+ "write down!");
        
    }


}