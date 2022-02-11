package com.umontreal.ift3913.h22.Javadocchecker;

/**
 * A base class for running the application.
 * 
 * Usage:
 * * $ codecheck.jar PATH_TO_REPOSITORY_TO_CHECK
 * If not argument is definied it will use the path PATH_TO_REPOSITORY in
 * config.properties
 * If no path is declared in PATH_TO_REPOSITORY, it will throw an error
 */
public class App {

    public static void main(String[] args) {
        System.out.println("\033[H\033[2J" + Helper.ANSI_RESET);
        try {
            readPathFromArg0(args);
        } catch (ArrayIndexOutOfBoundsException e) {
            readPathFromConfig();
        } catch (Exception e) {
            System.err.println("Something happened");
        }

        Parser.getAllFilesFromPath(PATH_TO_REPOSITORY);
    }

    /** For path to repository. */
    private static String PATH_TO_REPOSITORY;

    /**
     * Assigns the path to repository from a config file.
     */
    private static void readPathFromConfig() {
        try {
            PATH_TO_REPOSITORY = Helper.readConfig("PATH_TO_REPOSITORY");
            System.out.println(Helper.ANSI_YELLOW + "Using the path in config.properties " + Helper.ANSI_RESET
                    + PATH_TO_REPOSITORY);
        } catch (Exception e) {
            throw e;
        }

    }

    /**
     * Reads path from array of strings. First argument should
     * be the path.
     * 
     * @param args Array of string of arguments.
     */
    private static void readPathFromArg0(String[] args) {
        PATH_TO_REPOSITORY = args[0];
        System.out.println(Helper.ANSI_YELLOW + "Using the path in arg[0]  " + Helper.ANSI_RESET + PATH_TO_REPOSITORY);
    }
}
