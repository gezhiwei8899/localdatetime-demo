package com.gezhiwei.demo;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static com.gezhiwei.demo.BaseTool.getLocalDateFromStr;

public class ComputeWeek {


    public final static DateTimeFormatter yyyy_mm_dd = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    /**
     * 任意一天当周，周一的日期
     *
     * @param date
     * @return 日期 yyyy-MM-dd
     */
    public static LocalDate getMondayOfGivingDate(String date) {
        LocalDate localDate = getLocalDateFromStr(date);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        int offset = dayOfWeek.getValue() - 1;
        return localDate.minusDays(offset);
    }

    public static LocalDate getLastMondayOfGivingDate(String date) {
        return getMondayOfGivingDate(getLocalDateFromStr(date));
    }

    public static LocalDate getLastMondayOfGivingDate(LocalDate date) {
        return getMondayOfGivingDate(date.minusDays(6));
    }

    public static LocalDate getMondayOfGivingDate(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int offset = dayOfWeek.getValue() - 1;
        return date.minusDays(offset);
    }

    /**
     * 任意一天当周，周日的日期
     *
     * @param date
     * @return 日期 yyyy-MM-dd
     */
    public static LocalDate getSundayOfGivingDate(String date) {
        LocalDate localDate = getLocalDateFromStr(date);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        int offset = 7 - dayOfWeek.getValue();
        return localDate.plusDays(offset);
    }

    public static LocalDate getSundayOfGivingDate(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int offset = 7 - dayOfWeek.getValue();
        return date.plusDays(offset);
    }


    public static void printWeeksOfTwoDates(String time1, String time2, WeeksOfTwoDatesProcessor weeksOfTwoDatesProcessor) {
        LocalDate start = getLocalDateFromStr(time1);
        LocalDate end = getLocalDateFromStr(time2);
        printWeeksOfTwoDates(start, end, weeksOfTwoDatesProcessor);
    }


    public static void streamOfWeeksOfTwoDates(String time1, String time2, WeeksOfTwoDatesProcessor weeksOfTwoDatesProcessor) {
        printWeeksOfTwoDates(time1, time2, weeksOfTwoDatesProcessor);
    }


    public static void printWeeksOfTwoDates(LocalDate start, LocalDate end, WeeksOfTwoDatesProcessor weeksOfTwoDatesProcessor) {
        if (end.isBefore(start)) {
            printWeeksOfTwoDates(end, start, weeksOfTwoDatesProcessor);
        }
        long days = start.until(end, ChronoUnit.DAYS);
        if (days < 7) {
            weeksOfTwoDatesProcessor.invoker(0, null, null, "不足7天");
            return;
        }
        LocalDate mondayOfGivingDate = getMondayOfGivingDate(start);
        LocalDate p = mondayOfGivingDate.isBefore(start) ? mondayOfGivingDate.plusDays(7) : start;
        int i = 1;
        while (p.isBefore(end)) {
            LocalDate monday = p;
            LocalDate sunday = p.plusDays(6);
            if (sunday.isAfter(end)) {
                break;
            }
            weeksOfTwoDatesProcessor.invoker(i, monday, sunday, null);
            p = p.plusDays(7);
            i++;
        }
    }

    public static void main(String[] args) {
        printWeeksOfTwoDates("2023-01-01", "2023-12-23", new WeeksOfTwoDatesProcessor() {
            @Override
            public void invoker(int i, LocalDate start, LocalDate end, String msg) {
                if (null != msg) {
                    System.out.println(msg);
                    return;
                }
                System.out.println("第" + i + "周： " + start + " ~ " + end);
            }
        });
        LocalDate lastMondayOfGivingDate = getLastMondayOfGivingDate("2023-04-22");
        System.out.println(lastMondayOfGivingDate);
    }

}
