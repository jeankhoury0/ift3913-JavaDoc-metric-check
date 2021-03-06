package com.umontreal.ift3913.h22.proto.helpers;

import java.io.File;
import java.io.IOException;

/**
 * change git version to a specific commit
 */
public class changeCommitInFolder{
    
    public static void change(String commitHex) throws InterruptedException{
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("powershell", "-c", "cd tmp; cd *; ls; git reset --hard " + commitHex +";" ); 
        processBuilder.directory(new File("./"));

        try{
            Process process = processBuilder.start();

            // BufferedReader reader = new BufferedReader(
            //         new InputStreamReader(process.getInputStream()));

            // String line;
            // while ((line = reader.readLine()) != null) {
            //     System.out.println(line);
            // }
            
            int exitVal = process.waitFor();
            if (exitVal == 0) {
                process.destroy();
            } else {
                // abnormal...
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
