package com.umontreal.ift3913.h22.Javadocchecker;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class App 
{
    public static void main( String[] args )
    {

        try( InputStream input = new FileInputStream("./config.properties")){
            Properties prop = new Properties();
            
            prop.load(input);

            Parser.getAllFilesFromPath(prop.getProperty("PATH_TO_REPOSITORY"));
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        
        
    }
}
