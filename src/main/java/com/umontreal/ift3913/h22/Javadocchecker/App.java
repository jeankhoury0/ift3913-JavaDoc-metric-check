package com.umontreal.ift3913.h22.Javadocchecker;

public class App 
{
    public static void main( String[] args )
    {
        final String PATH_TO_REPOSITORY = Helper.readConfig("PATH_TO_REPOSITORY");
        Parser.getAllFilesFromPath(PATH_TO_REPOSITORY);

    }
}
