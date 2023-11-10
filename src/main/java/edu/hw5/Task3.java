package edu.hw5;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class Task3 {
    private static final List<DateTimeFormatter> TIME_FORMATTER_LIST = new ArrayList<>();
    private static final DateTimeFormatter FORMATTER1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter FORMATTER2 = DateTimeFormatter.ofPattern("yyyy-MM-d");
    private static final DateTimeFormatter FORMATTER3 = DateTimeFormatter.ofPattern("d/M/yyyy");
    private static final DateTimeFormatter FORMATTER4 = DateTimeFormatter.ofPattern("d/M/yy");

    static {
        TIME_FORMATTER_LIST.add(FORMATTER1);
        TIME_FORMATTER_LIST.add(FORMATTER2);
        TIME_FORMATTER_LIST.add(FORMATTER3);
        TIME_FORMATTER_LIST.add(FORMATTER4);
    }

    private Task3() {
    }

    public static Optional<LocalDate> parseDate(String string) {
        if (string == null) {
            throw new IllegalArgumentException("string is null");
        }
        LocalDate date = null;
        for (DateTimeFormatter formatter : TIME_FORMATTER_LIST) {
            try {
                date = LocalDate.parse(string, formatter);
            } catch (DateTimeParseException dateTimeParseException) {
                continue;
            }
            return Optional.of(date);
        }
        if ((string.matches("1 day ago")) || (string.equals("yesterday"))) {
            date = LocalDate.now().minusDays(1);
            return Optional.of(date);
        }
        if (string.equals("tomorrow")) {
            date = LocalDate.now().plusDays(1);
        }
        if (string.equals("today")) {
            date = LocalDate.now();
        }
        if (string.matches("\\d+ days ago")) {
            int days = Integer.parseInt(string.split(" ")[0]);
            date = LocalDate.now().minusDays(days);
            if (days == 1) {
                date = null;
            }
        }
        if (date == null) {
            return Optional.empty();
        } else {
            return Optional.of(date);
        }
    }
}
