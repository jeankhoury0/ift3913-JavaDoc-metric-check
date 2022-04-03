package com.umontreal.ift3913.h22.Proto;

import com.umontreal.ift3913.h22.Javadocchecker.Helper;
import com.umontreal.ift3913.h22.Proto.helpers.GitURL;
import com.umontreal.ift3913.h22.Proto.helpers.gitIdentifierList;

public class App {
    static GitURL gitURL;
    static gitIdentifierList gitIdentifierList; 
    
    public static void main(String[] args) {
        System.out.println("\033[H\033[2J" + Helper.ANSI_RESET);
        try {
            gitURL = new GitURL(args);
            System.out.println(gitURL.getURL());
            gitIdentifierList = new gitIdentifierList(gitURL);
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
