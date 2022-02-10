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

    public void closePW() {
        try {
            pw.close();
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

    public void parseMapToCSV(HashMap<String, PackageInfo> hm) {
        for (HashMap.Entry<String, PackageInfo> entry : hm.entrySet()) {
            PackageInfo pi = entry.getValue();
            appendLine(pi.toCSV());
        }
        closePW();

    }

    private void writeHeader() {
        try {
            pw.append("chemin, paquet, paquet_LOC, paquet_CLOC, paquet_DC, WCP, paquet_BC\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}