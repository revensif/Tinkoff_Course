package edu.hw5.Task8Test;

import edu.hw5.Task8;
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
        "0", "1", "000", "001", "110", "111", "10101"
    })
    void shouldCheckIsStringHaveAnOddLengthAndReturnTrue(String string) {
        boolean calculated = Task8.isStringHaveAnOddLength(string);
        assertThat(calculated).isTrue();
    }

    @ParameterizedTest
    @DisplayName("FirstRegExTest : Incorrect Input")
    @CsvSource({
        "''", "00", "01", "10", "11", "0010", "0111", "001111", "001100"
    })
    void shouldCheckIsStringHaveAnOddLengthAndReturnFalse(String string) {
        boolean calculated = Task8.isStringHaveAnOddLength(string);
        assertThat(calculated).isFalse();
    }

    @Test
    @DisplayName("FirstRegExTest : Incorrect Input")
    void shouldCheckIsStringHaveAnOddLengthAndThrowException() {
        assertThrows(
            IllegalArgumentException.class,
            () -> Task8.isStringHaveAnOddLength(null)
        );
    }
}
