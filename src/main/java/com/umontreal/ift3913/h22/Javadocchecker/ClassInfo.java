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
 * <ul>
 * <li>pathToFile: The asbolute path to the class
 * <li>packageName: The name packageName
 * <li>className: The name of the class
 * </ul>
 * The metrics are:
 * <ul>
 * <li>LOC: Line of code in the class
 * <li>CLOC: Line of comments in the class
 * <li>WMC: Weighted Methods per Class
 * <li>DC: Density of code in report to comment lines
 * <li>BC: Degree of the class being commented well
 * </ul>
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
     * Class constructor specifying the path of the class.
     * 
     * @param PathToFile the absolute path of the file.
     */
    public ClassInfo(Path PathToFile) {
        pathToFile = PathToFile;
        readByLine(pathToFile);
    }

    /**
     * Reads line by line the file of given path and execute a set of actions.
     * 
     * @param p the absolute path of the class to be read.
     * 
     * @see #actionForEachLine(String)
     */
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

    /**
     * Updates the Object ClassInfo about the given class'
     * <ul>
     * <li>Class name
     * <li>Package name
     * <li>Lines of code
     * <li>Lines of comments
     * <li>Number of methods
     * <li>Number of predicates
     * </ul>
     * 
     * @param line the given line to be read
     * 
     * @see #readByLine(Path)
     */
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

    /**
     * Extracts the class' name from lines of code.
     * 
     * @param line the line of code to be read.
     */
    private void extractClassName(String line) {
        String res = Helper.getIdentenfier(line, "public.* class|public.*interface|public.*enum");
        if (res != "") {
            this.className = res;
        }
    }

    /**
     * Extracts the package's name from lines of code.
     * 
     * @param line the line of code to be read.
     */
    private void extractPackageName(String line) {
        String res = Helper.getIdentenfier(line, "package");
        if (res != "") {
            this.packageName = res;
        }
    }

    /**
     * Returns a <code>Path</code> object of the current file being read
     * by the parser.
     * 
     * @return the <code>Path</code>.
     */
    public Path getPathToFile() {
        return pathToFile;
    }

    /**
     * Returns the package name of the current file being read
     * by the parser.
     * 
     * @return the package name.
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * Returns the class name of the current file being read
     * by the parser.
     * 
     * @return the class name.
     */
    public String getClassName() {
        return className;
    }

    /**
     * Returns the number of lines of code of the current file being read
     * by the parser.
     * 
     * @return the number of lines of code.
     */
    public int getClassLOC() {
        return LOC;
    }

    /**
     * Returns the number of commented lines of code of the current file
     * being read by the parser.
     * 
     * @return the number of commented lines of code.
     */
    public int getClassCLOC() {
        return CLOC;
    }

    /**
     * Returns the weighted methods of the current class being read
     * by the parser. The weighted methods is the weighted sum of 
     * McCabe's cyclomatic complexity of all methods of given class.
     * 
     * @return the weighted methods.
     */
    public int getClassWMC() {
        return WMC;
    }

    /**
     * Returns the density of code in report to the comment lines of
     * the current class being read by the parser.
     * 
     * @return the comment density.
     */
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
