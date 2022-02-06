package com.umontreal.ift3913.h22.Javadocchecker.csvGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * classCSVGenerator
 * Generate the CSV for the class
 */
public class ClassCSVGenerator {
    private final String fileName = "classe.csv";
    private FileWriter pw;
    
    public ClassCSVGenerator(){
        try {
            File f = new File(fileName);
            f.delete();
            pw = new FileWriter(f, true);
            writeHeader();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closePW(){
        try {
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void writeHeader(){
        try {
            pw.append("chemin, class, classe_LOC, classe_CLOC, classe_DC \n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void appendLine(String CSVline){
        try {
            pw.append(CSVline);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }
}