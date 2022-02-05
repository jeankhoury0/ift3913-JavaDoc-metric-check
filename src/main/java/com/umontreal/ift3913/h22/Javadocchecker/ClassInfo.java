package com.umontreal.ift3913.h22.Javadocchecker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Have all informations of the class
 */
public class ClassInfo {
    private Path pathToFile;
    private String packageName;
    private String className;
    private int LOC = 0;
    private int CLOC = 0;
    private float DC = 0;

    public ClassInfo(Path PathToFile) {
        pathToFile = PathToFile;

        // get total line of code
        // get total line of comment
        readByLine(pathToFile);
    }

    private void readByLine(Path p) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(p.toString()));
            for (String line; (line = br.readLine()) != null;) {
                metricIncrease(line);
                if (!classNameNotFound()) {
                    extractClassName(line);
                }

                if (!packageNameNotFound()) {
                    extractPackageName(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        DC = (float) CLOC / (float) LOC;
        // showOutput();

    }

    private void extractClassName(String line) {
        String res = Helper.getIdentenfier(line, "public class");
        if (res != "") {
            this.classNameWasFound = true;
            this.className = res;
        }
    }

    private Boolean classNameWasFound = false;

    private boolean classNameNotFound() {
        return this.classNameWasFound;
    }

    private void extractPackageName(String line) {
        String res = Helper.getIdentenfier(line, "package");
        if (res != "") {
            this.packageNameWasFound = true;
            this.packageName = res;
        }

    }

    private Boolean packageNameWasFound = false;

    private boolean packageNameNotFound() {
        return this.packageNameWasFound;
    }

    private void increaseLOC() {
        this.LOC += 1;
    }

    private void increaseCLOC() {
        this.CLOC += 1;
    }

    private void metricIncrease(String line) {
        Helper.isACommentary(line);
        if (Helper.isAValidLine(line)) {
            if (Helper.isACommentary(line)) {
                increaseCLOC();
            }
            increaseLOC();
        }

    }

    /**
     * Check that we are calculating a class
     * (not an ENUM or Inteface)
     * 
     * @return if the fields are valid
     */
    private boolean allTheFieldsAreValid() {
        if (className == null) {
            return false;
        }
        return true;

    }

    @SuppressWarnings("showOutput")
    private void showOutput() {
        System.out.println("===");
        System.out.println("Path: " + pathToFile);
        System.out.println("Package: " + packageName);
        System.out.println("Class: " + className);
        System.out.println("LOC: " + LOC);
        System.out.println("CLOC: " + CLOC);
        System.out.println("DC: " + DC);
        System.out.println("=== \n\n");
    }

    public String toCSV() {
        if (allTheFieldsAreValid()) {
            return (pathToFile + "," +
                    className + "," +
                    LOC + "," +
                    CLOC + "," +
                    DC+ "\n");
        } else
            return "";
    }

}
