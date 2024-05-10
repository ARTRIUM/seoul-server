package com.artrium.demo.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CommonUtils {

    public static String getLocalDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return date.format(formatter);
    }
}
