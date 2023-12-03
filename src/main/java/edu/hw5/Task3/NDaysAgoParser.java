package edu.hw5.Task3;

import java.time.LocalDate;
import java.util.Optional;

public class NDaysAgoParser extends Parser {
    @Override
    public Optional<LocalDate> parse(String string) {
        if (string.matches("\\d+ days ago")) {
            int days = Integer.parseInt(string.split(" ")[0]);
            if (days == 1) {
                return Optional.empty();
            } else {
                return Optional.of(LocalDate.now().minusDays(days));
            }
        }
        return Optional.empty();
    }
}
