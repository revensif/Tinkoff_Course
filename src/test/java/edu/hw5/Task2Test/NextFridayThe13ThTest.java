package edu.hw5.Task2Test;

import edu.hw5.Task2;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NextFridayThe13ThTest {

    @ParameterizedTest
    @DisplayName("nextFridayThe13Th(): Correct Input")
    @MethodSource("correctDates")
    void shouldGetNextFridayThe13Th(LocalDate date, LocalDate expected) {
        LocalDate calculated = Task2.nextFridayThe13Th(date);
        assertThat(calculated).isEqualTo(expected);
    }

    static Arguments[] correctDates() {
        return new Arguments[] {
            Arguments.of(LocalDate.of(1925, 2, 13), LocalDate.of(1925, 3, 13)),
            Arguments.of(LocalDate.of(2024, 9, 13), LocalDate.of(2024, 12, 13))
        };
    }

    @Test
    @DisplayName("nextFridayThe13Th(): Incorrect Input")
    void shouldGetNextFridayThe13ThAndThrowException() {
        assertThrows(IllegalArgumentException.class, () -> Task2.nextFridayThe13Th(null));
    }
}
