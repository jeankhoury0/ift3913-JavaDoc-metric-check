package com.umontreal.ift3913.h22.Javadocchecker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class HelperTest {
    @Test
    public void testGetIdentenfier() {
        String REGEX = "public.* class|class|public.*interface|public.*enum";
        assertEquals("", Helper.getIdentenfier("a line", REGEX));
        assertEquals("AbstractAnnotation", Helper.getIdentenfier("public abstract class AbstractAnnotation implements", 
                REGEX));
        assertEquals("DefaultPlotEditor",
                Helper.getIdentenfier("class DefaultPlotEditor extends JPanel implements ActionListener {", REGEX));

    }

    @Test
    public void testIsACommentary() {
        // normal style commentary
        assertTrue("line is a normal commentary //", Helper.isACommentary("// (C) Copyright 2009-2022, by David Gilbert and Contributors"));
        // Javadoc style commentary
        assertTrue("line is a start javadoc commentary /**",
                Helper.isACommentary("/** start java doc"));
        assertTrue("line is a javadoc commentary *",
                Helper.isACommentary("* @see #asd(string) middle javadoc commentary"));
        assertTrue("line is an end javadoc commentary */",
                Helper.isACommentary("*/"));

        // multiline style comments
        assertTrue("line is a start multiline commentary /*",
                Helper.isACommentary("/* start multiline commentary "));

        assertFalse("line is null", Helper.isACommentary(" "));
        assertFalse("line is not a commentary", Helper.isACommentary("system.out.println()"));

    }

    @Test
    public void testIsAValidLine() {

        assertFalse("line is null", Helper.isAValidLine(""));
        assertFalse("line is null but has indent", Helper.isAValidLine("    "));
        assertTrue("line is valid", Helper.isAValidLine("import static org.junit.Assert.assertTrue;"));
        assertTrue("line is valid and with indents", Helper.isAValidLine("    import static org.junit.Assert.assertTrue;"));

    }

    
}
