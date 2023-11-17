package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task7Test {

    @ParameterizedTest
    @DisplayName("FirstRegExTest : Correct Input")
    @CsvSource({
        "000", "010", "100", "110111", "110", "110100"
    })
    void shouldCheckIsStringHasMoreThan3SymbolsAndTheThirdSymbolIsZeroAndReturnTrue(String string) {
        boolean calculated = Task7.isStringHasMoreThan3SymbolsAndTheThirdSymbolIsZero(string);
        assertThat(calculated).isTrue();
    }

    @ParameterizedTest
    @DisplayName("FirstRegExTest : Incorrect Input")
    @CsvSource({
        "''", "0", "1", "00", "01", "10", "11", "001", "011", "001111", "001100"
    })
    void shouldCheckIsStringHasMoreThan3SymbolsAndTheThirdSymbolIsZeroAndReturnFalse(String string) {
        boolean calculated = Task7.isStringHasMoreThan3SymbolsAndTheThirdSymbolIsZero(string);
        assertThat(calculated).isFalse();
    }

    @Test
    @DisplayName("FirstRegExTest : Incorrect Input")
    void shouldCheckIsStringHasMoreThan3SymbolsAndTheThirdSymbolIsZeroAndThrowException() {
        assertThrows(
            IllegalArgumentException.class,
            () -> Task7.isStringHasMoreThan3SymbolsAndTheThirdSymbolIsZero(null)
        );
    }

    @ParameterizedTest
    @DisplayName("SecondRegExTest : Correct Input")
    @CsvSource({
        "0", "1", "00", "11", "000", "010", "101", "111", "1011", "0010"
    })
    void shouldCheckIsStringStartsAndEndsWithTheSameSymbolAndReturnTrue(String string) {
        boolean calculated = Task7.isStringStartsAndEndsWithTheSameSymbol(string);
        assertThat(calculated).isTrue();
    }

    @ParameterizedTest
    @DisplayName("SecondRegExTest : Incorrect Input")
    @CsvSource({
        "''", "01", "10", "100", "110", "001", "011", "001", "1010", "0111"
    })
    void shouldCheckIsStringStartsAndEndsWithTheSameSymbolAndReturnFalse(String string) {
        boolean calculated = Task7.isStringStartsAndEndsWithTheSameSymbol(string);
        assertThat(calculated).isFalse();
    }

    @Test
    @DisplayName("SecondRegExTest : Incorrect Input")
    void shouldCheckIsStringStartsAndEndsWithTheSameSymbolAndThrowException() {
        assertThrows(
            IllegalArgumentException.class,
            () -> Task7.isStringStartsAndEndsWithTheSameSymbol(null)
        );
    }

    @ParameterizedTest
    @DisplayName("ThirdRegExTest : Correct Input")
    @CsvSource({
        "0", "1", "00", "01", "10", "11", "000", "001", "010", "011", "100", "101", "110", "111"
    })
    void shouldCheckIsStringLengthIsNotLessThan1AndNotMoreThan3AndReturnTrue(String string) {
        boolean calculated = Task7.isStringLengthIsNotLessThan1AndNotMoreThan3(string);
        assertThat(calculated).isTrue();
    }

    @ParameterizedTest
    @DisplayName("ThirdRegExTest : Incorrect Input")
    @CsvSource({
        "''", "0000", "0001", "1001010", "1010"
    })
    void shouldCheckIsStringLengthIsNotLessThan1AndNotMoreThan3AndReturnFalse(String string) {
        boolean calculated = Task7.isStringLengthIsNotLessThan1AndNotMoreThan3(string);
        assertThat(calculated).isFalse();
    }

    @Test
    @DisplayName("ThirdRegExTest : Incorrect Input")
    void shouldCheckIsStringLengthIsNotLessThan1AndNotMoreThan3AndThrowException() {
        assertThrows(
            IllegalArgumentException.class,
            () -> Task7.isStringLengthIsNotLessThan1AndNotMoreThan3(null)
        );
    }
}
