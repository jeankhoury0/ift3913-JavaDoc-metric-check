package com.umontreal.ift3913.h22.proto;

import com.umontreal.ift3913.h22.Javadocchecker.Helper;
import com.umontreal.ift3913.h22.proto.helpers.GitURLConfig;
import com.umontreal.ift3913.h22.proto.helpers.GitIdentifierList;

public class App {
    static GitURLConfig gitURL;
    static GitIdentifierList gitIdentifierList; 
    
    public static void main(String[] args) {
        System.out.println("\033[H\033[2J" + Helper.ANSI_RESET);
        try {
            gitURL = new GitURLConfig(args);
            System.out.println(gitURL.getURL());
            gitIdentifierList = new GitIdentifierList(gitURL);
            gitIdentifierList.restrictAnalysis(5);
            CommitIterator commitIterator = new CommitIterator();
            commitIterator.iterateToAllCommits(gitIdentifierList.getIdentifierList());


            // System.out.println(gitIdentifierList.getIdentifierList());
            
            
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }

    }

    

    
}
