package com.umontreal.ift3913.h22.Javadocchecker.csvGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class classCSVGenerator extends csvFactory {
    private FileWriter pw;
    
    public classCSVGenerator(){
        try {
            pw = new FileWriter(new File("Classe.csv"),true);
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return;
    }
}
