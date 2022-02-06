package com.umontreal.ift3913.h22.Javadocchecker;

import java.util.ArrayList;

public class PackageInfo {
    private String packageName;
    private String path; 
    private ArrayList<ClassInfo> classes = new ArrayList<ClassInfo>();
    private int LOC;
    private int CLOC;
    

    public void add(ClassInfo ci) {
        if (packageName == null){
            packageName = ci.getPackageName();
            path = ci.getPathToFile().getParent().toString();
        }
        classes.add(ci);
        LOC += ci.getClassLOC();
        CLOC += ci.getClassCLOC();
    }

    public float getDC(){
        return (float) CLOC / (float) LOC;
    }

    public String toCSV() {
        return (path + "," +
                packageName + "," +
                LOC + "," +
                CLOC + "," +
                getDC() + "\n");
    }

    public String getPackageName() {
        return packageName;
    } 

}
