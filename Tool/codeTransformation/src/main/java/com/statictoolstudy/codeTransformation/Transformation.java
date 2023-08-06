package com.statictoolstudy.code_transformation;

import com.statictoolstudy.code_transformation.AddThisToFieldAccessExpr;
import com.statictoolstudy.code_transformation.AnonymousClassToLambda;
import com.statictoolstudy.code_transformation.ChangeOneVariableNameToMethodName;
import com.statictoolstudy.code_transformation.MoveMethodToNestedClass;

import com.statictoolstudy.code_transformation.fileProcess.DirExplorer;

import java.io.File;
import java.io.FileNotFoundException;

public class Transformation {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java CommandLineArgs <MutationOperator> <testCasePath>");
            return;
        }

        File projectDir = new File(args[1]);
        System.out.println(args[0]);
        System.out.println(args[1]);
        
        new DirExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
            if ("addthis".equals(args[0])) {
                AddThisToFieldAccessExpr addThisToFieldAccessExpr = new AddThisToFieldAccessExpr();
                addThisToFieldAccessExpr.ParseCodeFile(level, path, file);
            } else if ("lambdaexpr".equals(args[0])) {
                AnonymousClassToLambda anonymousClassToLambda = new AnonymousClassToLambda();
                anonymousClassToLambda.tolambda(level, path, file);
            } else if ("renamesymbols".equals(args[0])) {
                ChangeOneVariableNameToMethodName changeOneVariableNameToMethodName = new ChangeOneVariableNameToMethodName();
                changeOneVariableNameToMethodName.ParseCodeFile(level, path, file);
            } else if ("nestedclass".equals(args[0])) {
                MoveMethodToNestedClass moveMethodToNestedClass = new MoveMethodToNestedClass();
                moveMethodToNestedClass.ParseCodeFile(level, path, file);
            }
        }).explore(projectDir);
    }
}
