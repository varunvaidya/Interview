package com.ubs.opsit.interviews;

import java.util.function.BiFunction;
import java.util.function.Function;

public class BerlinClock {

    private String seconds;
    private String hoursFirstRow;
    private String hoursSecondRow;
    private String minutesFirstRow;
    private String minutesSecondRow;

    public String getSeconds() {
        return seconds == null ? "": seconds+"\r\n";
    }

    public String getHoursFirstRow() {
        return hoursFirstRow == null ? "" : hoursFirstRow+"\r\n";
    }

    public String getHoursSecondRow() {
        return hoursSecondRow == null ? "" : hoursSecondRow+"\r\n";
    }

    public String getMinutesFirstRow() {
        return minutesFirstRow == null ? "" : minutesFirstRow+"\r\n";
    }

    public String getMinutesSecondRow() {
        return minutesSecondRow == null ? "" : minutesSecondRow;
    }

    private BerlinClock(){

    }

    @Override
    public String toString() {
        return  getSeconds()+  getHoursFirstRow() +
                 getHoursSecondRow() +
                 getMinutesFirstRow() +
                getMinutesSecondRow();
    }

    public static class BerlinClockBuilder {

        private String secondsRow;
        private String firstHourRow;
        private String secondHourRow;
        private String firstMinuteRow;
        private String secondMinuteRow;
        private String hourRowSign = "R";
        private String minuteRowSign = "Y";
        private String secondsRowSign = "Y";
        private String quarterSign = "R";
        private final String offColor = "O";

        public BerlinClockBuilder setSecondsRowSign(String color){
            this.secondsRowSign = color;
            return this;
        }

        public BerlinClockBuilder setHourRowSign(String color){
            this.hourRowSign = color;
            return this;
        }

        public BerlinClockBuilder setMinutesRowSign(String color){
            this.minuteRowSign = color;
            return this;
        }

        public BerlinClockBuilder setQuarterSign(String color){
            this.quarterSign = color;
            return this;
        }

        public BerlinClockBuilder buildSecondsRow(Integer seconds){
            Function<Integer,String> secondsRow = i -> i % 2 == 0 ? secondsRowSign : offColor;
            this.secondsRow = secondsRow.apply(seconds);
            return this;
        }

        public BerlinClockBuilder buildHoursFirstRow(Integer hours){
            BiFunction<Integer,Integer,String> firstHourRow = (lamps, number) -> BerlinClockUtil.getRowData(lamps,Math.floorDiv(number,5), hourRowSign);
            this.firstHourRow = firstHourRow.apply(4,hours);
            return this;
        }

        public BerlinClockBuilder buildHoursSecondRow(Integer hours){
            BiFunction<Integer,Integer,String> secondHourRow = (lamps,number) -> BerlinClockUtil.getRowData(lamps,Math.floorMod(number,5), hourRowSign);
            this.secondHourRow = secondHourRow.apply(4,hours);
            return this;
        }

        public BerlinClockBuilder buildMinutefirstRow(Integer minutes){
            BiFunction<Integer,Integer,String> firstMinuteRow = (lamps,number) -> BerlinClockUtil.getRowData(lamps,Math.floorDiv(number,5), minuteRowSign, quarterSign);
            this.firstMinuteRow = firstMinuteRow.apply(11,minutes);
            return this;
        }

        public BerlinClockBuilder buildMinutesecondRow(Integer minutes){
            BiFunction<Integer,Integer,String> secondMinuteRow = (lamps,number) -> BerlinClockUtil.getRowData(lamps,Math.floorMod(number,5), minuteRowSign);
            this.secondMinuteRow = secondMinuteRow.apply(4,minutes);
            return this;
        }

        public BerlinClock build(){

            BerlinClock berlinClock = new BerlinClock();
            berlinClock.seconds = this.secondsRow;
            berlinClock.hoursFirstRow = this.firstHourRow;
            berlinClock.hoursSecondRow = this.secondHourRow;
            berlinClock.minutesFirstRow = this.firstMinuteRow;
            berlinClock.minutesSecondRow = this.secondMinuteRow;
            return berlinClock;
        }
    }

}
