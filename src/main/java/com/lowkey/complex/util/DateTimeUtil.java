package com.lowkey.complex.util;

import cn.hutool.core.date.DateTime;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil extends DateTime {

    public final String DATETIME = "yyyy-MM-dd HH:mm:ss";

    public String currentDate() {
        return DateFormat.getInstance().format(LocalDate.now());
    }

    public String currentDateTime() {
        return DateTimeFormatter.ofPattern(DATETIME).format(LocalDateTime.now());
    }
}
