package com.springboot.rentcar.common.formatter;

import com.springboot.rentcar.common.util.FileLoad;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class DateFormatter {
    private static ResourceBundle bundle = FileLoad.loadResourceBundle("configuration");

    public static String convertLocalDateTimeToString(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern(bundle.getString("local.date.format.pattern")));
    }

    public static LocalDateTime convertStringToLocalDateTime(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(bundle.getString("local.date.format.pattern")));
    }
}
