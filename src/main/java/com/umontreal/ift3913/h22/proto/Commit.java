package com.umontreal.ift3913.h22.proto;

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
}
