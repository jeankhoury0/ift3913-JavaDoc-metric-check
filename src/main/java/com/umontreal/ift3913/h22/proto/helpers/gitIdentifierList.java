package com.umontreal.ift3913.h22.proto.helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Obtain all the commits id of the project
 */
public class gitIdentifierList {
    ProcessBuilder processBuilder = new ProcessBuilder();

    private ArrayList<String> identifierList = new ArrayList<String>();
    private gitURL gitUrl;
    
    public gitIdentifierList(gitURL repoURL){
        this.gitUrl = repoURL; 
        getAllIndentifier();
    }
    
    public ArrayList<String> getIdentifierList(){
        return identifierList;
    }


    private void getAllIndentifier(){
        //TODO remove the folder at the beguining of exec "rmdir -rf *"
        
        processBuilder.command("bash", "-c", "cd tmp; echo getting clone of " + "; git clone " + gitUrl.getURL()
                + "; cd *; git rev-list master");
        processBuilder.directory(new File("./"));

        try {
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                this.identifierList.add(line);
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
