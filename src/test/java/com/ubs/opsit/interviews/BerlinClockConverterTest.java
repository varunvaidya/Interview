package com.ubs.opsit.interviews;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BerlinClockConverterTest {

    TimeConverter timeConverter = new BerlinClockConverter();

    // test invalid time response
    @Test
    public void convertTimeInvalidTimeTest() {
        Assert.assertEquals("Invalid time passed", timeConverter.convertTime("25:00:0"));
    }

    // test midnight
    @Test
    public void testMidNight() {
        Assert.assertEquals("Y\r\n" +
                "OOOO\r\n" +
                "OOOO\r\n" +
                "OOOOOOOOOOO\r\n" +
                "OOOO", timeConverter.convertTime("00:00:0"));
    }

    @Test
    public void testTimeWithQuarterPast() {
        Assert.assertEquals("Y\r\n" +
                "RROO\r\n" +
                "RRRO\r\n" +
                "YYRYYRYYROO\r\n" +
                "YOOO", timeConverter.convertTime("13:46:02"));

        Assert.assertEquals("O\r\n" +
                "RROO\r\n" +
                "RRRO\r\n" +
                "YYROOOOOOOO\r\n" +
                "YYOO", timeConverter.convertTime("13:17:01"));
    }

    @Test
    public void testBerlinClockWIthDifferentSigns(){
        String secondSign = "S";
        String hourSign = "H";
        String minuteSign = "M";
        String quarterSign = "Q";
        TimeConverter timeConverter1 = aTime -> {
            if(BerlinClockUtil.isValidTime(aTime)){
                List<Integer> rows = Arrays.asList(aTime.split(":")).stream().map(Integer::parseInt).collect(Collectors.toList());
                return new BerlinClock.BerlinClockBuilder()
                        .setSecondsRowSign(secondSign)
                        .setHourRowSign(hourSign)
                        .setMinutesRowSign(minuteSign)
                        .setQuarterSign(quarterSign)
                        .buildSecondsRow(rows.get(2))
                        .buildHoursFirstRow(rows.get(0))
                        .buildHoursSecondRow(rows.get(0))
                        .buildMinutefirstRow(rows.get(1))
                        .buildMinutesecondRow(rows.get(1))
                        .build().toString();

            }
            return "Invalid time passed";
        };
        Assert.assertEquals("S\r\n" +
                "HHOO\r\n" +
                "HHHO\r\n" +
                "MMQMMQMMQOO\r\n" +
                "MOOO", timeConverter1.convertTime("13:46:02"));
    }

    @Test
    public void testBerlinClockWithSingleTimeUnits() {
        // test seconds row which blinks every two seconds
        Assert.assertEquals("Y\r\n", new BerlinClock.BerlinClockBuilder().buildSecondsRow(16).build().toString());

        //test all hours row for passed time
        Assert.assertEquals("RRRO"+"\r\n"+"ROOO"+"\r\n", new BerlinClock.BerlinClockBuilder().buildHoursFirstRow(16).buildHoursSecondRow(16).build().toString());

        // test top minute row which
        Assert.assertEquals("YYRYYROOOOO\r\n", new BerlinClock.BerlinClockBuilder().buildMinutefirstRow(30).build().toString());

    }
}