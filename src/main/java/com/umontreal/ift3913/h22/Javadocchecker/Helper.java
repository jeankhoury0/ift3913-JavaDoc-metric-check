package com.umontreal.ift3913.h22.Javadocchecker;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {

    public static String readConfig(String property){
        try( InputStream input = new FileInputStream("./config.properties")){
            Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty(property);
        } catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Match the signature of a class or package via regex
     * 
     * @param line is the line that might contain a keyword
     * @param keyword is the keyword wanted (eg: Class, Package)
     * @return
     *  - the identifier if the keyword was found
     *  - "" if was not found
     */
    public static String getIdentenfier(String line, String keyword){
        String REGEX = "(" + keyword + ")(\\s)([.a-zA-Z\\d]*)";
        Pattern stringPattern = Pattern.compile(REGEX);
        Matcher m = stringPattern.matcher(line);
        if (m.find()){
            return(m.group(3));
        } 
            return "";
    }


    /**
     * Line is non empty
     * @param line in analysis
     * @return if line is valid
     */
    public static boolean isAValidLine(String line){
        if (line.trim().isEmpty()) {
         return false;
        }
        return true;
    }

    public static boolean isACommentary(String line){
        String COMMENT_REGEX = readConfig("COMMENT_REGEX");
        Pattern stringPattern = Pattern.compile(COMMENT_REGEX);
        Matcher m = stringPattern.matcher(line);
        return m.find();
    }  
}
