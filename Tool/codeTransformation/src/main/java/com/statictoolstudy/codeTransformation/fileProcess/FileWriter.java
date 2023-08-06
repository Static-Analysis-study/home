package com.statictoolstudy.code_transformation.fileProcess;

import java.io.File;
import java.io.IOException;

public class FileWriter{

    public static void writeContent(String content, String path){
        java.io.FileWriter fw = null;
        try {
            File file = new File(path);
            if (!file.exists()) {    // if file doesn't exists, then create it
                if (file.isDirectory()) {
                    file.mkdirs(); 
                } else {
                    file.getParentFile().mkdirs(); 
                    file.createNewFile(); 
                }
            }
            fw = new java.io.FileWriter(file,false);
            fw.write(content);
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                if (fw != null){
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
