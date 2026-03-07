package com.ticketflow.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String format(LocalDateTime date) {
        if (date == null)
            return "";
        return date.format(FORMATTER);
    }

    public static String formatDate(LocalDateTime date) {
        if (date == null)
            return "";
        return date.format(DATE_FORMATTER);
    }

    public static LocalDateTime parse(String dateStr) {
        if (dateStr == null || dateStr.isBlank())
            return null;
        return LocalDateTime.parse(dateStr, FORMATTER);
    }
}
