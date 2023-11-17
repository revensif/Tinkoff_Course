package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

public class YesterdayTodayTomorrow1DayAgoParser extends Parser {

    @Override
    public Optional<LocalDate> parse(String string) {
        return switch (string) {
            case "1 day ago", "yesterday" -> Optional.of(LocalDate.now().minusDays(1));
            case "tomorrow" -> Optional.of(LocalDate.now().plusDays(1));
            case "today" -> Optional.of(LocalDate.now());
            default -> Optional.empty();
        };
    }
}
