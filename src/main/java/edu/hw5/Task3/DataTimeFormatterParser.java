package edu.hw5.Task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataTimeFormatterParser extends Parser {
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

    @Override
    public Optional<LocalDate> parse(String string) {
        LocalDate date;
        for (DateTimeFormatter formatter : TIME_FORMATTER_LIST) {
            try {
                date = LocalDate.parse(string, formatter);
            } catch (DateTimeParseException dateTimeParseException) {
                continue;
            }
            return Optional.of(date);
        }
        return Optional.empty();
    }
}
