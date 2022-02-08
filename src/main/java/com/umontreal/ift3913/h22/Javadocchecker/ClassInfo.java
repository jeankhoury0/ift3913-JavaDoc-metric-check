package com.umontreal.ift3913.h22.Javadocchecker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Have all informations of the selected class
 * Basic informations are:
 * - pathToFile: The asbolute path to the class
 * - packageName: The name packageName
 * - className: The name of the class
 * The metrics are:
 * - LOC: Line of code in the class
 * - CLOC: Line of comments in the class
 * - WMC: Weighted Methods per Class
 * - DC: Density of code in report to comment lines
 * - BC: DC / WMC
 */
public class ClassInfo {
    private Path pathToFile;
    private String packageName;
    private String className;
    private int LOC = 0;
    private int CLOC = 0;
    private int WMC = 0;
    private float DC = 0;
    private float BC = 0;
    private int methodCount = 0;

    /**
     * Constructor to each class
     * 
     * @param PathToFile is the absolute path the file
     */
    public ClassInfo(Path PathToFile) {
        pathToFile = PathToFile;
        readByLine(pathToFile);
    }

    private void readByLine(Path p) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(p.toString()));
            for (String line; (line = br.readLine()) != null;) {
                actionForEachLine(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void actionForEachLine(String line) {
        extractClassName(line);
        extractPackageName(line);
        metricIncrease(line);
        predicateIncrease(line);
        methodIncrease(line);
        WMC += methodCount;
        DC = getDC();
        BC = getBC();
    }

    private void extractClassName(String line) {
        String res = Helper.getIdentenfier(line, "public.* class|public.*interface|public.*enum");
        if (res != "") {
            this.className = res;
        }
    }

    private void extractPackageName(String line) {
        String res = Helper.getIdentenfier(line, "package");
        if (res != "") {
            this.packageName = res;
        }
    }

    public Path getPathToFile() {
        return pathToFile;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getClassName() {
        return className;
    }

    public int getClassLOC() {
        return LOC;
    }

    public int getClassCLOC() {
        return CLOC;
    }

    public int getClassWMC() {
        return WMC;
    }

    private float getDC() {
        if (LOC == 0) {
            return 0;
        } else {
            return (float) CLOC / (float) LOC;
        }
    }

    private float getBC() {
        if (WMC == 0) {
            return 0;
        } else {
            return getDC() / (float) WMC;
        }
    }

    private void increaseLOC() {
        this.LOC += 1;
    }

    private void increaseCLOC() {
        this.CLOC += 1;
    }

    private void increaseMethodCount(int number) {
        this.methodCount += number;
    }

    private void increaseWMC(int number) {
        this.WMC += number;
    }

    private void predicateIncrease(String line) {
        if (Helper.isAValidLine(line)) {
            if (!Helper.isACommentary(line)) {
                String REGEX = "(?:^|\\W)(if|while|switch|for)(?:$|\\W)";
                Pattern stringPattern = Pattern.compile(REGEX);
                Matcher m = stringPattern.matcher(line);
                increaseWMC((int) m.results().count());
            }
        }
    }

    private void metricIncrease(String line) {
        if (Helper.isAValidLine(line)) {
            if (Helper.isACommentary(line)) {
                increaseCLOC();
            }
            increaseLOC();
        }

    }

    private void methodIncrease(String line) {
        String REGEX = "(\\w+)(\\s+)([a-z]\\w*)(\\s*)(\\()";
        Pattern stringPattern = Pattern.compile(REGEX);
        Matcher m = stringPattern.matcher(line);
        increaseMethodCount((int) m.results().count());
    }

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
        return (pathToFile + "," +
                className + "," +
                LOC + "," +
                CLOC + "," +
                DC + "," +
                WMC + "," +
                BC + "\n");
    }
}
