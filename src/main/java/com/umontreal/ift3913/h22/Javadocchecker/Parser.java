package com.umontreal.ift3913.h22.Javadocchecker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.regex.PatternSyntaxException;


public class Parser {
    /**
     * Get all files from the path specified
     * @param pathToRepo is the String to the repository we want to read file from
     */
    public static void getAllFilesFromPath(String pathToRepo) {

        Path p = Paths.get(pathToRepo);
        FileVisitor<Path> fv = new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path p, BasicFileAttributes attrs)
                    throws IOException {
                // todo EXECUTE CODE HERE
                // System.out.println(p);
                // CHECK PARENT OF EACH FILE extract package
                // add to a hashmap 
                if (isAValidFile(p)){
                    readByLine(p);
                }
                return FileVisitResult.CONTINUE;
            }
        };

        try {
            Files.walkFileTree(p, fv);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readByLine(Path p){
        try{
            BufferedReader br = new BufferedReader(new FileReader(p.toString()));
                for (String line; (line = br.readLine()) != null;) {
                    System.out.println(line);
                }
        } catch(IOException e){
            e.printStackTrace();
        }
        
    }


    private static Boolean isAValidFile(Path p){
        try {
            String LANGUAGE_EXTENSION = Helper.readConfig("LANGUAGE_EXTENSION").toLowerCase();
            String actualFileExtension = p.toFile().toString().split("\\.")[1].toLowerCase();
            if(LANGUAGE_EXTENSION.matches(actualFileExtension)){
                return true;
            }
        } catch (PatternSyntaxException e) {
            // catch a file that has no file extension
            return false;
        }
        return false;
    }

}
