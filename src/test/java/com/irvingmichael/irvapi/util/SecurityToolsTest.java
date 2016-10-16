package com.irvingmichael.irv.util;

import org.apache.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Aaron Anderson on 10/14/16.
 */
public class SecurityToolsTest {

    private final Logger log = Logger.getLogger(this.getClass());

    @Test
    public void hashString() throws Exception {
        String testString = SecurityTools.hashString("bacon");
        log.debug(testString);
        assertEquals("Bad hash returned", "��\u0007\u00034.$�j�d��\u0005=�\u007F,�\u000F\u0010R���*�\n" +
                "\fw�", testString);
    }

}