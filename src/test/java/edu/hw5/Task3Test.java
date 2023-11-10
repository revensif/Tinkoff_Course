package edu.hw5;

import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task3Test {

    @ParameterizedTest
    @DisplayName("Task3Test: Correct Input")
    @MethodSource("correctDates")
    void shouldParseDate(String string, Optional<LocalDate> expected) {
        assertThat(Task3.parseDate(string)).isEqualTo(expected);
    }

    static Arguments[] correctDates() {
        return new Arguments[] {
            Arguments.of("2020-10-10", Optional.of(LocalDate.of(2020, 10, 10))),
            Arguments.of("2020-12-2", Optional.of(LocalDate.of(2020, 12, 2))),
            Arguments.of("1/3/1976", Optional.of(LocalDate.of(1976, 3, 1))),
            Arguments.of("1/3/20", Optional.of(LocalDate.of(2020, 3, 1))),
            Arguments.of("tomorrow", Optional.of(LocalDate.now().plusDays(1))),
            Arguments.of("today", Optional.of(LocalDate.now())),
            Arguments.of("yesterday", Optional.of(LocalDate.now().minusDays(1))),
            Arguments.of("1 day ago", Optional.of(LocalDate.now().minusDays(1))),
            Arguments.of("2234 days ago", Optional.of(LocalDate.now().minusDays(2234)))
        };
    }

    @ParameterizedTest
    @DisplayName("Task3Test: Incorrect Input")
    @MethodSource("incorrectDates")
    void shouldParseDateAndReturnEmptyOptional(String string, Optional<LocalDate> expected) {
        assertThat(Task3.parseDate(string)).isEqualTo(expected);
    }

    static Arguments[] incorrectDates() {
        return new Arguments[] {
            Arguments.of("2020-10-0", Optional.empty()),
            Arguments.of("202-10-10", Optional.empty()),
            Arguments.of("2020-13-1", Optional.empty()),
            Arguments.of("tomorro", Optional.empty()),
            Arguments.of("today2", Optional.empty()),
            Arguments.of("yesterdayy", Optional.empty()),
            Arguments.of("5 day ago", Optional.empty()),
            Arguments.of("1 days ago", Optional.empty()),
            Arguments.of("", Optional.empty()),
        };
    }

    @Test
    @DisplayName("Task3Test: Incorrect Input")
    void shouldParseDateAndThrowException() {
        assertThrows(IllegalArgumentException.class, () -> Task3.parseDate(null));
    }
}
