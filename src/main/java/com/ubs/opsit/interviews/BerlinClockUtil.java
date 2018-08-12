package com.ubs.opsit.interviews;

import org.apache.commons.lang.StringUtils;

import java.util.Arrays;

public class BerlinClockUtil {

    public static boolean isValidTime(String time){
        String[] timedata = time.split(":");
        if (timedata.length != 3)
            return false;
        if (Arrays.stream(timedata).anyMatch(number -> number.length() == 0)) {
            return false;
        }
        if (!Arrays.stream(timedata).allMatch(number -> StringUtils.isNumeric(number))) {
            return false;
        }
        if (Integer.parseInt(timedata[0]) > 24 || Integer.parseInt(timedata[1]) > 59
                || Integer.parseInt(timedata[2]) > 59) {
            return false;
        }
        return true;

    }

    public static String getRowData(int lamps, int onSigns, String onSign) {
        StringBuilder out = new StringBuilder();
        for (int i = 1; i <= lamps; i++) {
            if(i <= onSigns)
                out.append(onSign);
            else
                out.append("O");
        }
        return out.toString();
    }

    public static String getRowData(int lamps, int onSigns, String onSign, String quarterSign) {
        StringBuilder out = new StringBuilder();
        for (int i = 1; i <= lamps; i++) {
            if(i <= onSigns && (i > 1 && i % 3 == 0))
                out.append(quarterSign);
            else if (i <= onSigns)
                out.append(onSign);
            else
                out.append("O");
        }
        return out.toString();
    }
}
