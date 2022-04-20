package com.umontreal.ift3913.h22.proto;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import com.umontreal.ift3913.h22.proto.helpers.CSVReader;

/**
 * All the info on a commit
 */
public class Commit {
    String commitID;
    int classCount; 
    int mWMC;
    double mcBC;


    @Override
    public String toString() {
        return commitID + " " + classCount;
    }


    public void runAnalysis(){
        ProcessBuilder pb = new ProcessBuilder();
        pb.command("powershell", "-c", "java -jar codecheck.jar tmp;");
        pb.directory(new File("."));
        
        try {
            Process process = pb.start();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                // System.out.println("powershell was executed perfectly!");
                process.destroy();
            } else {
                System.out.println("d");
                // abnormal...
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getMetrics();
    }

    private void getMetrics(){
        CSVReader csvReader = new CSVReader("classes.csv");
        csvReader.read();
        try {
            mcBC = csvReader.getTotalMcBC()/classCount;
            mWMC = csvReader.getTotalWMC()/classCount;
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public String CSVLineBuilder(){
        return(commitID + "," + classCount + "," + mWMC + "," + mcBC + "\n" );
    }
}
