package com.umontreal.ift3913.h22.Javadocchecker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

import javax.swing.text.PlainView;

/**
 * Have all informations of the class 
 */
public class ClassInfo {
    private Path pathToFile;
    private String className;
    private String packageName; 

    public ClassInfo(Path PathToFile){
        pathToFile = PathToFile;

        // get total line of code
        // get total line of comment
        readByLine(pathToFile);
    }


    private void readByLine(Path p) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(p.toString()));
            for (String line; (line = br.readLine()) != null;) {
                // Execute stuff here
                if (classNameNotFound()){
                    extractClassName(line);
                }
                
                if (packageNameNotFound()){
                    extractPackageName(line);
                }

                
                // System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    private void extractClassName(String line){
        String res = Helper.getIdentenfier(line, "public class");
        if ( res != "") {
            this.classNameWasFound =true;
            className = res;
        }
    }

    private Boolean classNameWasFound = false;
    private boolean classNameNotFound(){
        return this.classNameWasFound;
    }

    private void extractPackageName(String line) {
        String res = Helper.getIdentenfier(line, "package");
        if (res != "") {
            this.packageNameWasFound = true;
            packageName = res;
        }
        
    }

    private Boolean packageNameWasFound = false;
    private boolean packageNameNotFound() {
        return this.packageNameWasFound;
    }


}
