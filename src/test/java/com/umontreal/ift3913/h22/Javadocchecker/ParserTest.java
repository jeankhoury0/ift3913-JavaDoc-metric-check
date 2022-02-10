package com.umontreal.ift3913.h22.Javadocchecker;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class ParserTest {

    @Test
    public void testIsAValidFile() {
        assertFalse("Non java file", Parser.isAValidFile(Paths.get("c:\\data\\myfile.txt")));
        assertTrue("java file", Parser.isAValidFile(Paths.get("c:\\data\\myfile.java")));
        assertFalse("non specific file", Parser.isAValidFile(Paths.get("c:\\data\\myfile")));
    }

}
