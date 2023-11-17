package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task2Test {

    @ParameterizedTest
    @DisplayName("getAllFridaysThe13Th(): Correct Input")
    @MethodSource("years")
    void shouldGetAllFridaysThe13Th(int year, List<LocalDate> expected) {
        List<LocalDate> calculated = Task2.getAllFridaysThe13Th(year);
        assertThat(calculated).isEqualTo(expected);
    }

    static Arguments[] years() {
        return new Arguments[] {
            Arguments.of(
                1925,
                List.of(
                    LocalDate.of(1925, 2, 13),
                    LocalDate.of(1925, 3, 13),
                    LocalDate.of(1925, 11, 13)
                )
            ),
            Arguments.of(
                2024,
                List.of(
                    LocalDate.of(2024, 9, 13),
                    LocalDate.of(2024, 12, 13)
                )
            ),
        };
    }

    @Test
    @DisplayName("getAllFridaysThe13Th(): Incorrect Input")
    void shouldGetAllFridaysThe13ThAndThrowException() {
        assertThrows(IllegalArgumentException.class, () -> Task2.getAllFridaysThe13Th(-10));
    }

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
