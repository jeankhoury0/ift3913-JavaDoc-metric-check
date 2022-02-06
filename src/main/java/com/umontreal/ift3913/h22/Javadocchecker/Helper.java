package com.umontreal.ift3913.h22.Javadocchecker;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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
     * 
     * @param line is the line that might contain a keyword
     * @param keyword is the keyword wanted (eg: Class, Package)
     * @return
     *  - null if the keyword was found
     *  - 
     */
    public static String getIdentifier(String line, String keyword){
        return ""; // TODO Stub
    }
    
}
