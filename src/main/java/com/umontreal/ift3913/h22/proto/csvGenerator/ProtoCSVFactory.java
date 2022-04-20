package com.umontreal.ift3913.h22.Proto.csvGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ProtoCSVFactory {
    private final String fileName = "proto.csv";
    private FileWriter pw;

    /**
     * Reintilised the protoCSVFactory
     * - Delete the old file
     * - Add the file
     */
    public ProtoCSVFactory(){
        try{
            File f = new File(fileName);
            f.delete();
            pw = new FileWriter(f, true);
            writeHeader();
        } catch( Exception e){
            e.printStackTrace();
        }
        

    }

    private void writeHeader() throws IOException{
        pw.append("id_version, NC, mWMC, mcBC\n");
    }

    public void appendLine(String CSVline){
        try{
            pw.append(CSVline);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }
    

    public void closePW() {
        try {
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
