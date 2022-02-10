package com.umontreal.ift3913.h22.Javadocchecker.csvGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ClassCsvFactory {

    private final String fileName = "classes.csv";
    private FileWriter pw;

    public ClassCsvFactory() {
        try {
            File f = new File(fileName);
            f.delete();
            pw = new FileWriter(f, true);
            writeHeader();
        } catch (Exception e) {
            e.printStackTrace();        
        }
    }

    public void closePW() {
        try {
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeHeader() {
        try {
            pw.append("chemin, class, classe_LOC, classe_CLOC, classe_DC, WMC, classe_BC\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void appendLine(String CSVline) {
        try {
            pw.append(CSVline);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }
    
}
