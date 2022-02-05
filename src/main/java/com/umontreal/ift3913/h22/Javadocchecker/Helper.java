package com.umontreal.ift3913.h22.Javadocchecker;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.Flow.Subscriber;
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

    
    
}
