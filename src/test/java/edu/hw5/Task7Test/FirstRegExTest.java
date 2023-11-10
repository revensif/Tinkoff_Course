package edu.hw5.Task7Test;

import edu.hw5.Task7;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FirstRegExTest {

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
}
