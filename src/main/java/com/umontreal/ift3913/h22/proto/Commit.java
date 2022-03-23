package com.umontreal.ift3913.h22.proto;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * All the info on a commit
 */
public class Commit {
    String commitID;
    int classCount; 


    @Override
    public String toString() {
        return commitID + " " + classCount;
    }


    public void runAnalysis(){
        ProcessBuilder pb = new ProcessBuilder();
        pb.command("powershell", "-c", "java -jar codecheck.jar .tmp;");
        pb.directory(new File("./"));
        
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
                System.out.println("Bash was executed perfectly!");
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
    }
}
