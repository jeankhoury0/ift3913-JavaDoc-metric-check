package com.umontreal.ift3913.h22.Javadocchecker;

import java.util.ArrayList;

public class PackageInfo {
    private String packageName;
    private String path; 
    private ArrayList<ClassInfo> classes = new ArrayList<ClassInfo>();
    private int LOC;
    private int CLOC;
    private int WCP;
    

    public void add(ClassInfo ci) {
        if (packageName == null){
            packageName = ci.getPackageName();
            path = ci.getPathToFile().getParent().toString();
        }
        classes.add(ci);
        LOC += ci.getClassLOC();
        CLOC += ci.getClassCLOC();
        WCP += ci.getClassWMC();
    }

    public float getDC(){
        if (LOC == 0) {
            return 0;
        } else {
            return (float) CLOC / (float) LOC;
        }
    }

    public float getBC() {
        if (WCP == 0) {
            return 0;
        } else {
            return getDC() / (float) WCP;
        }
    }

    public String toCSV() {
        float DC = getDC();
        float BC = getBC();
        return (path + "," +
                packageName + "," +
                LOC + "," +
                CLOC + "," +
                DC + "," +
                WCP + "," +
                BC + "\n");
    }

    public String getPackageName() {
        return packageName;
    } 

}
