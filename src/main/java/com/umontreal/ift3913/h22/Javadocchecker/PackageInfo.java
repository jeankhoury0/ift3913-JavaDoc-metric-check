package com.umontreal.ift3913.h22.Javadocchecker;

import java.util.ArrayList;

/**
 * Have all informations of the selected package
 * Basic informations are:
 * - packageName: The name of the package
 * - pathToPackage: The asbolute path to the package
 * - classes: A list of all the attached classes
 * The metrics are:
 * - LOC: Line of code in the package
 * - CLOC: Line of comments in the package
 * - WMP: Weighted Methods per package
 * - DC: Density of code in report to comment lines
 * - BC: DC / WMC
 */
public class PackageInfo {
    private String packageName;
    private String pathToPackage;
    private ArrayList<ClassInfo> classes = new ArrayList<ClassInfo>();
    private int LOC;
    private int CLOC;
    private int WCP;
    private float BC;
    private float DC;

    /**
     * Updates the package info by adding to it the given
     * <code>ClassInfo</code> object.
     * 
     * @param ci the <code>ClassInfo</code>.
     */
    public void add(ClassInfo ci) {
        if (packageName == null) {
            packageName = ci.getPackageName();
            pathToPackage = ci.getPathToFile().getParent().toString();
        }
        classes.add(ci);
        LOC += ci.getClassLOC();
        CLOC += ci.getClassCLOC();
        WCP += ci.getClassWMC();
    }

    /**
     * Gets the comment density of this package.
     * 
     * @return the comment density of this package.
     */
    public float getDC() {
        if (LOC == 0) {
            return 0;
        }
        return (float) CLOC / (float) LOC;
    }

    /**
     * Gets the degree this package is well commented.
     * 
     * @return the degree this package is well commented.
     */
    public float getBC() {
        if (WCP == 0) {
            return 0;
        }
        return getDC() / (float) WCP;
    }

    /**
     * Gets this package's name.
     * 
     * @return this package's name.
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * Generate a line of comma separated value for the csv parsing
     * 
     * @return the <code>PackageInfo</code> reprensentation formatted for CSV.
     */
    public String toCSV() {
        DC = getDC();
        BC = getBC();
        return (pathToPackage + "," +
                packageName + "," +
                LOC + "," +
                CLOC + "," +
                DC + "," +
                WCP + "," +
                BC + "\n");
    }
}
