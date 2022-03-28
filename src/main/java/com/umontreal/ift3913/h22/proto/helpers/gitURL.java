package com.umontreal.ift3913.h22.Proto.helpers;

import com.umontreal.ift3913.h22.Javadocchecker.Helper;

/**
 * Get the git url either from:
 * - config.properties files
 * - input
 */
public class GitURL {
    private String url;

    public GitURL(String[] args){
        if (args.length == 1){
            url = readGithubURLFromArg0(args);
            return;
        }
        url = readGithubURLFromConfig();
    }


    public String getURL(){
        return url;
    }


    private String readGithubURLFromConfig() {
        try{
            System.out.println(Helper.ANSI_YELLOW + "Using the url in config.properties " + Helper.ANSI_RESET);
            return Helper.readConfig("DEFAULT_URL_TO_GIT_REPO");
            
        } catch (Exception e) {
            throw e;
        }
    }

    private String readGithubURLFromArg0(String[] args){
        try {
            return args[0];
        } catch (Exception e) {
            e.getStackTrace();
            return "";
        }
    }
}
