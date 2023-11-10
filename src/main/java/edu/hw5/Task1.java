package edu.hw5;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class Task1 {

    private Task1() {
    }

    public static Duration calculateAverageTime(String[] strings) {
        if ((strings == null) || (strings.length < 1)) {
            throw new IllegalArgumentException("Array is null or empty");
        }
        Duration time = Duration.ZERO;
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");
        for (String string : strings) {
            String[] dates = string.split(" - ");
            try {
                LocalDateTime date1 = LocalDateTime.parse(dates[0], formatter);
                LocalDateTime date2 = LocalDateTime.parse(dates[1], formatter);
                Duration duration = Duration.between(date1, date2);
                time = time.plus(duration);
            } catch (DateTimeParseException dateTimeParseException) {
                throw new DateTimeException("Wrong Input (Example: 2022-03-12, 20:20 - 2022-03-12, 23:50)");
            }
        }
        return time.dividedBy(strings.length);
    }
}
