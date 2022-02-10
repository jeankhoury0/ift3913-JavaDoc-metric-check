package com.umontreal.ift3913.h22.Javadocchecker;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;

import com.umontreal.ift3913.h22.Javadocchecker.csvGenerator.ClassCsvFactory;
import com.umontreal.ift3913.h22.Javadocchecker.csvGenerator.PackageCsvFactory;

/**
 * Parser
 * Parsing informations from the specified path and the starting
 * point for analysis of package and class
 */
public class Parser {

    private static HashMap<String, PackageInfo> packageMap = new HashMap<String, PackageInfo>();
    final static ClassCsvFactory classCSV = new ClassCsvFactory();

    /**
     * Get all files recursively from the path specified
     * It works if using folder or files
     * 
     * @param pathToRepo is the String to the repository we want to read file from
     */
    public static void getAllFilesFromPath(String pathToRepo) {
        Path p = Paths.get(pathToRepo);
        FileVisitor<Path> fv = new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path p, BasicFileAttributes attrs)
                    throws IOException {
                operationOnEachPath(p);
                return FileVisitResult.CONTINUE;
            }
        };

        try {
            Files.walkFileTree(p, fv);
            // * Parse CSV
            PackageCsvFactory packageCSV = new PackageCsvFactory();
            packageCSV.parseMapToCSV(packageMap);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            classCSV.closePW();
        }
    }

    /**
     * For each path:
     * - we check if the path is valid (from the config doc)
     * - we run the analysis for class on the file
     * - we append to the package hashmap to perform analysis
     * of the package
     * 
     * @param p is the path to the class
     */
    private static void operationOnEachPath(Path p) {
        if (isAValidFile(p)) {
            ClassInfo ci = new ClassInfo(p);
            classCSV.appendLine(ci.toCSV());
            appendToPackageMap(ci);
        }
    }

    protected static Boolean isAValidFile(Path p) {
        try {
            
            String LANGUAGE_EXTENSION = Helper.readConfig("LANGUAGE_EXTENSION").toLowerCase();
            String actualFileExtension = p.toFile().toString().split("\\.")[1].toLowerCase();
            if ((LANGUAGE_EXTENSION.matches(actualFileExtension)) && (!isAnInformationalClass(p))) {
                return true;
            }
        } catch (Exception e) {
            // catch a file that has no file extension
            return false;
        }
        return false;
    }
    
    /**
     * Check if the file match package-info or module-info. Those files are excluded
     * as they are not real classes.
     * 
     * @param p is the path to the class
     * @return if a class is informational
     */
    private static Boolean isAnInformationalClass(Path p){
        String fileName = p.getFileName().toString();
        if ((fileName.matches("package-info.java")) || fileName.matches("module-info.java")) return true;
        return false;
    }

    private static void appendToPackageMap(ClassInfo ci) {
        PackageInfo pi = packageMap.get(ci.getPackageName());
        if (pi == null) {
            packageMap.put(ci.getPackageName(), new PackageInfo());
            System.out.print("\n Package: " + ci.getPackageName() + " \t \t \t");
        } else {
            System.out.print(".");
        }

        pi = packageMap.get(ci.getPackageName());
        pi.add(ci);
    }

}
