package com.umontreal.ift3913.h22.Javadocchecker.csvGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.umontreal.ift3913.h22.Javadocchecker.PackageInfo;

/**
 * packageCSVGenerator
 * Generate the CSV for the package
 */
public class packageCSVGenerator {
    private final String fileName = "paquets.csv";
    private FileWriter pw;

    public packageCSVGenerator() {
        try {
            pw = new FileWriter(new File(fileName), true);
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
        for (Map.Entry<String, PackageInfo> entry : hm.entrySet()) {
            PackageInfo pi = entry.getValue();
            appendLine(pi.toCSV());
        }
        closePW();

    }

    private void writeHeader() {
        try {
            pw.append("chemin, paquet, paquet_LOC, paquet_CLOC, paquet_DC \n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}