package com.umontreal.ift3913.h22.proto.helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Obtain all the commits id of the project
 */
public class GitIdentifierList {
    ProcessBuilder processBuilder = new ProcessBuilder();

    private ArrayList<String> identifierList = new ArrayList<String>();
    private GitURLConfig gitUrl;
    
    public GitIdentifierList(GitURLConfig repoURL){
        this.gitUrl = repoURL; 
        getAllIndentifier();
    }
    
    public ArrayList<String> getIdentifierList(){
        return identifierList;
    }


    private void getAllIndentifier(){
        
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
    

    // restrict the analysis to only <pourcentageOfCommits>% of all commits
    /**
     * Restrict the analysis by reducing the list to a random sample of commits
     * @param pourcentageOfCommits is the pourcentage of commits we want to analyse (set to 5 in normal case)
     */
    public void restrictAnalysis(int pourcentageOfCommits){
        int identifierListsize = identifierList.size();
        int restrictiveIdentifierListSize = (int) Math.floor(identifierListsize * (pourcentageOfCommits*0.01));
        ArrayList<String> restrictiveIdentifierList = new ArrayList<String>();

        //copied from https://stackoverflow.com/a/50035694
        Random random = new Random();
        ArrayList<Integer> restrictiveCommitIndex = (ArrayList<Integer>) (random.ints(0, identifierListsize).distinct().limit(restrictiveIdentifierListSize).boxed().sorted().collect(Collectors.toList()));

        restrictiveCommitIndex.forEach((i) -> {
            restrictiveIdentifierList.add(identifierList.get(i));
        });
        System.out.println("Sample size reduced from " + identifierListsize + " to -> " + restrictiveIdentifierListSize);

        identifierList = restrictiveIdentifierList;
    }

}
