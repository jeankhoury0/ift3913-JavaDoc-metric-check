package com.umontreal.ift3913.h22.Proto.helpers;

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
    private GitURL gitUrl;
    
    public gitIdentifierList(GitURL repoURL){
        this.gitUrl = repoURL; 
        getAllIndentifier();
    }
    
    public ArrayList<String> getIdentifierList(){
        return identifierList;
    }


    private void getAllIndentifier(){
        //TODO remove the folder at the beguining of exec "rmdir -rf *"
        
        processBuilder.command("bash", "-c", "cd tmp; rm -rf * .; git clone " + gitUrl.getURL()
                + "; cd *; git rev-list HEAD;");
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
                System.out.println("powershell was executed perfectly!");
                process.destroy();
            } else {
                System.out.println("ERROR");
                // abnormal...
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
