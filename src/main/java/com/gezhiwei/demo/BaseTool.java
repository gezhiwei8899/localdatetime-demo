package com.gezhiwei.demo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BaseTool {


    public static LocalDate getLocalDateFromStr(String date) {
        try {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            throw e;
        }
    }
}
