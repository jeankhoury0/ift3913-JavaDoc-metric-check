package com.umontreal.ift3913.h22.Javadocchecker;

/**
 * App
 * Usage:
 * * $ codecheck.jar PATH_TO_REPOSITORY_TO_CHECK
 * If not argument is definied it will use the path PATH_TO_REPOSITORY in config.properties
 * If no path is declared in PATH_TO_REPOSITORY, it will throw an error
 */
public class App 
{
    public static void main( String[] args )
    {   System.out.flush();
        String PATH_TO_REPOSITORY;
        try{
            PATH_TO_REPOSITORY = Helper.readConfig("PATH_TO_REPOSITORY");
            System.out.println("Using the path in config.properties - " + PATH_TO_REPOSITORY);
        } catch (ArrayIndexOutOfBoundsException e) {
            PATH_TO_REPOSITORY = args[0];
            System.out.println("Using the path in arg[0] - " + PATH_TO_REPOSITORY);
        }

        Parser.getAllFilesFromPath(PATH_TO_REPOSITORY);
    }
}
