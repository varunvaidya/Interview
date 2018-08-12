package com.ubs.opsit.interviews;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class BerlinClockUtilTest {

    // test passed time
    @Test
    public void isValidTime() {
        // test invalid time
        assertFalse(BerlinClockUtil.isValidTime("30:00:00"));
        assertFalse(BerlinClockUtil.isValidTime("HH:MM:SS"));
        assertFalse(BerlinClockUtil.isValidTime("23:61:00"));
        assertFalse(BerlinClockUtil.isValidTime("00:35:62"));
        assertFalse(BerlinClockUtil.isValidTime("00:35:62"));
        assertFalse(BerlinClockUtil.isValidTime("000:35:62"));
        assertFalse(BerlinClockUtil.isValidTime("00:350:62"));

        // test valid time
        assertTrue(BerlinClockUtil.isValidTime("00:35:24"));
        assertTrue(BerlinClockUtil.isValidTime("23:23:23"));
        assertTrue(BerlinClockUtil.isValidTime("23:59:59"));
    }

    @Test
    public void getRowData() {
        // test formation of particular row
        assertEquals("Y", BerlinClockUtil.getRowData(1,1,"Y"));
         // negative test formation of top hour row if all lamps are ON
        assertNotEquals("OOOO", BerlinClockUtil.getRowData(4,4,"R"));
        // test formation of top hour row if all lamps are ON
        assertEquals("RRRR", BerlinClockUtil.getRowData(4,4,"R"));
        // test formation of row string length is equal to lamps
        assertEquals(4, BerlinClockUtil.getRowData(4,0,"R").length());
        // test row data with quarter sign
        assertEquals("YYRYYRYYROO", BerlinClockUtil.getRowData(11,9,"Y","R"));


    }
}