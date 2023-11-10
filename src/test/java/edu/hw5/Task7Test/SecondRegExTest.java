package edu.hw5.Task7Test;

import edu.hw5.Task7;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SecondRegExTest {

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
}
