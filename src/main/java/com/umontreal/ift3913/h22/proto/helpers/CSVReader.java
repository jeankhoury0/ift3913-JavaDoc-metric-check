package com.umontreal.ift3913.h22.Proto.helpers;

import java.io.BufferedReader;
import java.io.FileReader;

public class CSVReader {

    final int CLASSE_DC_POS = 4;
    final int WMC_POS = 5;

    private String fileName;
    private int totalWMC = 0;
    private double totalmcBC = 0;
    public CSVReader(String fileName){
        this.fileName = fileName;
    }

    public int getTotalWMC(){
        return totalWMC;
    }

    public double getTotalMcBC(){
        return totalmcBC;
    }
    
    public void read(){
            String line = "";
            String splitBy = ",";
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            // remove the header
            br.readLine();
            while((line = br.readLine()) != null){
                String[] commitLine = line.split(splitBy);
                // System.out.println(commitLine[CLASSE_DC_POS] + " " + commitLine[WMC_POS]);
                totalWMC += Integer.parseInt(commitLine[WMC_POS]);
                totalmcBC += Double.parseDouble(commitLine[CLASSE_DC_POS]);

            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("something is not right \n \n \n j");
        }

    }
}
