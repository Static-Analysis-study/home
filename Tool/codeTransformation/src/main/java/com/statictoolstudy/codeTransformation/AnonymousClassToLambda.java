package com.statictoolstudy.code_transformation;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.ModifierVisitor;
import com.github.javaparser.ast.visitor.Visitable;
import com.github.javaparser.utils.CodeGenerationUtils;
import com.github.javaparser.utils.Log;
import com.github.javaparser.utils.SourceRoot;
import com.github.javaparser.JavaParserAdapter;

import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.ReceiverParameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.AssignExpr.Operator;
import com.github.javaparser.ast.expr.ConditionalExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.UnaryExpr;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.comments.BlockComment;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.nodeTypes.NodeWithAnnotations;

import com.github.javaparser.ast.*;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.modules.*;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.type.*;
import com.github.javaparser.utils.Pair;
import com.github.javaparser.ParseProblemException;

import com.statictoolstudy.code_transformation.fileProcess.DirExplorer;
import com.statictoolstudy.code_transformation.fileProcess.FileWriter;
import com.github.javaparser.StaticJavaParser;
import com.google.common.base.Strings;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 *   This class is used to transform anonymous class to lambda
 */

public class AnonymousClassToLambda {

    public static void tolambda(int level, String path, File file){
        List<String> fieldVariables = new ArrayList<>();
        if (path.indexOf(".java") != -1) {
            System.out.println(Strings.repeat("=", path.length()+50));
            System.out.println("Now processing ---" + file);
            try {
                
                CompilationUnit cu = StaticJavaParser.parse(file);
                int [] file_num = new int[1];
                file_num[0]=1;
                int [] change_num = new int[1];
                change_num[0]=0;
                // get all comment and delete it (case some problem by comment)
                // List<Comment> comments = cu.getAllComments();
                // comments.forEach(c -> c.remove());

                cu.accept(new ModifierVisitor <Void>() {

                    @Override
                    public Visitable visit(FieldDeclaration n, Void arg) {
                        
                        NodeList<VariableDeclarator> variables = n.getVariables();                   
                        return super.visit(n, arg); 
                    }

                    @Override
                    public Visitable visit(ObjectCreationExpr n, Void arg) {
                        NodeList<BodyDeclaration<?>> anonymousClassBody = modifyList(n.getAnonymousClassBody(), arg);
                        NodeList<Expression> arguments = n.getArguments();
                        Expression scope = n.getScope().map(s -> (Expression) s.accept(this, arg)).orElse(null);
                        ClassOrInterfaceType type = (ClassOrInterfaceType) n.getType().accept(this, arg);
                        NodeList<Type> typeArguments = modifyList(n.getTypeArguments(), arg);
                        Comment comment = n.getComment().map(s -> (Comment) s.accept(this, arg)).orElse(null);
                        // System.out.println("anonymousClassBody--->"+anonymousClassBody);
                        if (type == null)
                            return null;
                        
                        int method_num = 0;
                        int annotation_Flag = 0;
                        if (anonymousClassBody != null){
                            
                            for(BodyDeclaration anonyClass : anonymousClassBody){
                                
                                if(anonyClass.isMethodDeclaration()){
                                    method_num++;
                                    if(anonyClass.isAnnotationPresent("Override")){
                                        annotation_Flag = 1;
                                        List<Node> allnodes = anonyClass.getChildNodes(); 
                                    }
                                
                                }

                            }
                            if(annotation_Flag==1 && method_num == 1){

                                Node method_node = anonymousClassBody.get(0);
                                List<Node> allnodes = anonymousClassBody.get(0).getChildNodes();
                                
                                MethodDeclaration meth_Dec = StaticJavaParser.parseMethodDeclaration(method_node.toString());
                                BlockStmt body = meth_Dec.getBody().map(s -> (BlockStmt) s.accept(this, arg)).orElse(null);  
                                NodeList<Parameter> parameters = modifyList(meth_Dec.getParameters(), arg);
                    
                                // Create new lambdaExpr
                                LambdaExpr new_lambda = new LambdaExpr();
                                new_lambda.setBody(body);
                                new_lambda.setParameters(parameters);
                                new_lambda.setEnclosingParameters(true);
                                Node lambdaNode = new_lambda.getParentNodeForChildren();
                                // replace
                                n.replace(lambdaNode);

                                writeFileToDict(file,cu.toString());

                                file_num[0] = file_num[0]+1;

                            }
                        }
                        return super.visit(n, arg); 
                    }

                    /**
                    * modifyList in modifierVisitor
                    */
                    private <N extends Node> NodeList<N> modifyList(NodeList<N> list, Void arg) {
                        return (NodeList<N>) list.accept(this, arg);
                    }
                
                    private <N extends Node> NodeList<N> modifyList(Optional<NodeList<N>> list, Void arg) {
                        return list.map(ns -> modifyList(ns, arg)).orElse(null);
                    }

                    private EnclosedExpr addParentExpr(Expression expr) {
                        EnclosedExpr enclosedExpr = new EnclosedExpr();
                        enclosedExpr.setInner(expr);
                        return enclosedExpr;
                    }
                    
                    private Expression removeParentExpr(EnclosedExpr parentExpr) {
                        Expression expr = parentExpr.getInner();
                        parentExpr.remove(expr);
                        return expr;
                    }
                    
                    private MethodCallExpr equalsExpr(Expression leftExpr, Expression rightExpr) {
                        return new MethodCallExpr(leftExpr, "equals", new NodeList<>(rightExpr));
                    }

                }, null);

                // writeFileToDict(file,cu.toString());

                System.out.println(); // empty line

            } catch (IOException e) {
                e.printStackTrace();
            }catch (ParseProblemException e) {
                System.err.println("Parsing error in file: " + file + " - " + e.getMessage());
            }
        }
    }   

    public static void writeFileToDict(File file, String content){

        String new_filepath= "";
        new_filepath = file.toString().replace("originalCase","lambdaCase");
        FileWriter.writeContent(content,new_filepath);
        System.out.println("[INFO]"+new_filepath+ "write down!");
        
    }

}