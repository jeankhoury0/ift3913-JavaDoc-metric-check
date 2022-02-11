package com.umontreal.ift3913.h22.Javadocchecker.csvGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/** 
 * A class to generate a .csv file for all <code>ClassInfo</code> objects.
 */
public class ClassCsvFactory {

    private final String fileName = "classes.csv";
    private FileWriter pw;

    /**
     * Writes the classes.csv file in current directory. Will overwrite
     * file if it already exists.
     */
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

    /**
     * Closes the file writer after it has finished its job.
     */
    public void closePW() {
        try {
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the first row of the document, being the header.
     */
    private void writeHeader() {
        try {
            pw.append("chemin, class, classe_LOC, classe_CLOC, classe_DC, WMC, classe_BC\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Appends the <code>ClassInfo</code> information into the document.
     * 
     * @param CSVline the line to be inserted into the document.
     */
    public void appendLine(String CSVline) {
        try {
            pw.append(CSVline);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }
    
}
