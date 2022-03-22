package com.umontreal.ift3913.h22.proto;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.umontreal.ift3913.h22.Javadocchecker.Helper;
import com.umontreal.ift3913.h22.Javadocchecker.Parser;
import com.umontreal.ift3913.h22.proto.helpers.changeCommitInFolder;

/**
 * Class responsible to:
 * - Get each commit
 * - Get the number of class of each commit
 */
public class CommitIterator {

    public ArrayList <Commit> commitData = new ArrayList<>();
    public CommitIterator(){

    }

    public void iterateToAllCommits(ArrayList<String> commitIdList){
        
        for (String commitId:commitIdList){
            Commit commit = new Commit();
            commit.commitID = commitId;
            int classCount = 0;
            System.out.println(Helper.ANSI_YELLOW + "Commit id: " + commitId + Helper.ANSI_RESET);
           
            try {
                changeCommitInFolder.change(commitId);
                Parser.getAllFilesFromPath(new File("./tmp/").toString());
                walkFilePath("./tmp/");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }            

        }
    }

    private void walkFilePath(String p){
        // String fileExtension = "."+ Helper.readConfig("LANGUAGE_EXTENSION").toLowerCase();
        // System.out.println(fileExtension);
        try (Stream<Path> walk = Files.walk(Paths.get(p))) {
            // We want to find only regular files
            List<Object> result = walk.filter(Files::isRegularFile)
                                        .filter(a -> a.getFileName().toString().endsWith(".java"))
                                        .map(x -> x.toString()).collect(Collectors.toList());

            for(Object path:result){
                classCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(classCount);

    }
    
}
