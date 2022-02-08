package com.umontreal.ift3913.h22.Javadocchecker;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.regex.PatternSyntaxException;

import com.umontreal.ift3913.h22.Javadocchecker.csvGenerator.ClassCSVGenerator;
import com.umontreal.ift3913.h22.Javadocchecker.csvGenerator.PackageCSVGenerator;

public class Parser {
    /**
     * Get all files from the path specified
     * 
     * @param pathToRepo is the String to the repository we want to read file from
     */
    private static HashMap<String, PackageInfo> packageMap = new HashMap<String, PackageInfo>();
    final static ClassCSVGenerator classCSV = new ClassCSVGenerator();

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
                operationOnEachPath(p);
                return FileVisitResult.CONTINUE;
            }
        };

        try {
            Files.walkFileTree(p, fv);
            // * Parse CSV
            PackageCSVGenerator packageCSV = new PackageCSVGenerator();
            packageCSV.parseMapToCSV(packageMap);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            classCSV.closePW();
        }
    }

    // TODO methods operations
    private static void operationOnEachPath(Path p){
        if (isAValidFile(p)) {
            ClassInfo ci = new ClassInfo(p);
            classCSV.appendLine(ci.toCSV());
            appendToPackageMap(ci);
        }
    }

    private static Boolean isAValidFile(Path p) {
        try {
            String LANGUAGE_EXTENSION = Helper.readConfig("LANGUAGE_EXTENSION").toLowerCase();
            String actualFileExtension = p.toFile().toString().split("\\.")[1].toLowerCase();
            if (LANGUAGE_EXTENSION.matches(actualFileExtension)) {
                return true;
            }
        } catch (PatternSyntaxException e) {
            // catch a file that has no file extension
            return false;
        }
        return false;
    }
    
    private static void appendToPackageMap(ClassInfo ci) {
        PackageInfo pi = packageMap.get(ci.getPackageName());
        if (pi == null) {
            packageMap.put(ci.getPackageName(), new PackageInfo());
            System.out.print("P");
        } else {
            System.out.print(".");
        }

        pi = packageMap.get(ci.getPackageName());
        pi.add(ci);
    }

}
