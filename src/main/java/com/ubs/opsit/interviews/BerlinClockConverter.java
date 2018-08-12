package com.ubs.opsit.interviews;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BerlinClockConverter implements TimeConverter {


    @Override
    public String convertTime(String time){
        if(BerlinClockUtil.isValidTime(time)){
            List<Integer> rows = Arrays.asList(time.split(":")).stream().map(Integer::parseInt).collect(Collectors.toList());
            return new BerlinClock.BerlinClockBuilder()
                    .buildSecondsRow(rows.get(2))
                    .buildHoursFirstRow(rows.get(0))
                    .buildHoursSecondRow(rows.get(0))
                    .buildMinutefirstRow(rows.get(1))
                    .buildMinutesecondRow(rows.get(1))
                    .build().toString();
        }
        return "Invalid time passed";
    }
}
