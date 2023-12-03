package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class Parser {

    public Parser() {
    }

    public static Optional<LocalDate> parseDate(String string) {
        if (string == null) {
            throw new IllegalArgumentException("string is null");
        }
        List<Parser> list = new ArrayList<>();
        list.add(new DataTimeFormatterParser());
        list.add(new YesterdayTodayTomorrow1DayAgoParser());
        list.add(new NDaysAgoParser());
        for (Parser parser : list) {
            Optional<LocalDate> optionalLocalDate = parser.parse(string);
            if (optionalLocalDate.isPresent()) {
                return optionalLocalDate;
            }
        }
        return Optional.empty();
    }

    public abstract Optional<LocalDate> parse(String string);
}
