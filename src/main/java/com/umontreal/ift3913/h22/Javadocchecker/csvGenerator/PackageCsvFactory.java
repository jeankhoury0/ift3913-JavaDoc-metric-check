package com.umontreal.ift3913.h22.Javadocchecker.csvGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import com.umontreal.ift3913.h22.Javadocchecker.PackageInfo;

/**
 * PackageCsvFactory
 * Generate the CSV for the package
 */
public class PackageCsvFactory {
    private final String fileName = "paquets.csv";
    private FileWriter pw;

    /**
     * Writes the paquets.csv file in current directory. Will overwrite
     * file if it already exists.
     */
    public PackageCsvFactory(){
        try {
            File f = new File(fileName);
            f.delete();
            pw = new FileWriter(f, true);
            writeHeader();

        } catch (IOException e) {
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
     * Appends the <code>PackageInfo</code> information into the document.
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

    /**
     * Parses every class in a package to gather information and inserts
     * it to the document.
     * 
     * @param hm the hashmap linking every class to its package parent.
     */
    public void parseMapToCSV(HashMap<String, PackageInfo> hm) {
        for (HashMap.Entry<String, PackageInfo> entry : hm.entrySet()) {
            PackageInfo pi = entry.getValue();
            appendLine(pi.toCSV());
        }
        closePW();

    }

    /**
     * Writes the first row of the document, being the header.
     */
    private void writeHeader() {
        try {
            pw.append("chemin, paquet, paquet_LOC, paquet_CLOC, paquet_DC, WCP, paquet_BC\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}