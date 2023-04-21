package com.gianco.RequesterBot.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtils {
    public static DateTimeFormatter formatterFromJson = DateTimeFormatter.ofPattern("yyyyMMdd", Locale.ITALIAN);
    public static DateTimeFormatter formatterToNotify = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ITALIAN);

    public static LocalDate getLocalDateFromJson(String date) {
        return LocalDate.parse(date, formatterFromJson);
    }

    public static String formatLocalDate(LocalDate date) {
        return formatterToNotify.format(date);
    }
}
