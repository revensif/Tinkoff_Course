package edu.hw5;

import java.time.DateTimeException;
import java.time.Duration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task1Test {

    @ParameterizedTest
    @DisplayName("Task1Test: Correct Input")
    @MethodSource("correctDates")
    void shouldCalculateAverageTime(String[] dates, Duration expected) {
        Duration calculated = Task1.calculateAverageTime(dates);
        assertThat(calculated).isEqualTo(expected);
    }

    static Arguments[] correctDates() {
        return new Arguments[] {
            Arguments.of(
                new String[] {"2022-03-12, 20:20 - 2022-03-12, 23:50", "2022-04-01, 21:30 - 2022-04-02, 01:20"},
                Duration.ofMinutes(220)
            ),
            Arguments.of(
                new String[] {"2022-03-12, 12:20 - 2022-03-12, 23:50", "2022-04-01, 21:30 - 2022-04-05, 11:20"},
                Duration.ofMinutes(2920)
            )
        };
    }

    @Test
    @DisplayName("Task1Test: Incorrect Input")
    void shouldCalculateAverageTimeAndThrowException() {
        assertThrows(IllegalArgumentException.class, () -> Task1.calculateAverageTime(null));
        assertThrows(IllegalArgumentException.class, () -> Task1.calculateAverageTime(new String[] {}));
        String[] dates1 = {"2022-03-12, 20:20  2022-03-12, 23:50"};
        assertThrows(DateTimeException.class, () -> Task1.calculateAverageTime(dates1));
        String[] dates2 = {"2022-03-12, 20:20 - 2022-03-12"};
        assertThrows(DateTimeException.class, () -> Task1.calculateAverageTime(dates2));
    }
}
