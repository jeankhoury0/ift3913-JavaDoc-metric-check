package com.umontreal.ift3913.h22.proto.helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * change git version to a specific commit
 */
public class changeCommitInFolder{
    
    public static void change(String commitHex) throws InterruptedException{
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", "", "cd tmp; cd *; ls; git reset --hard " + commitHex +";" ); 
        processBuilder.directory(new File("./"));

        try{
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            
            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println("\n");
                process.destroy();
            } else {
                // abnormal...
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
