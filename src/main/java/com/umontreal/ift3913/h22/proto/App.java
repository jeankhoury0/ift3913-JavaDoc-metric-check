package com.umontreal.ift3913.h22.proto;

import com.umontreal.ift3913.h22.Javadocchecker.Helper;
import com.umontreal.ift3913.h22.proto.helpers.gitIdentifierList;
import com.umontreal.ift3913.h22.proto.helpers.gitURL;

public class App {
    static gitURL gitURL;
    static gitIdentifierList gitIdentifierList; 
    
    public static void main(String[] args) {
        System.out.println("\033[H\033[2J" + Helper.ANSI_RESET);
        try {
            gitURL = new gitURL(args);
            System.out.println(gitURL.getURL());
            gitIdentifierList = new gitIdentifierList(gitURL);
            // System.out.println(gitIdentifierList.getIdentifierList());

            CommitIterator commitIterator = new CommitIterator();
            commitIterator.iterateToAllCommits(gitIdentifierList.getIdentifierList());


            // System.out.println(gitIdentifierList.getIdentifierList());
            
            
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }

    }

    

    
}
