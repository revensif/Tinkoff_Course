package edu.hw5.Task8Test;

import edu.hw5.Task8;
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
        "1", "00", "01", "100", "101", "110", "111", "0110", "11001", "011001"
    })
    void shouldCheckIsStringStartsWith0AndHaveAnOddLengthOrStartsWith1AndHaveAnEvenLengthAndReturnTrue(String string) {
        boolean calculated = Task8.isStringStartsWith0AndHaveAnOddLengthOrStartsWith1AndHaveAnEvenLength(string);
        assertThat(calculated).isTrue();
    }

    @ParameterizedTest
    @DisplayName("SecondRegExTest : Incorrect Input")
    @CsvSource({
        "''", "0", "10", "11", "000", "010", "1010", "1110", "01011", "101101"
    })
    void shouldCheckIsStringStartsWith0AndHaveAnOddLengthOrStartsWith1AndHaveAnEvenLengthAndReturnFalse(String string) {
        boolean calculated = Task8.isStringStartsWith0AndHaveAnOddLengthOrStartsWith1AndHaveAnEvenLength(string);
        assertThat(calculated).isFalse();
    }

    @Test
    @DisplayName("SecondRegExTest : Incorrect Input")
    void shouldCheckIsStringStartsWith0AndHaveAnOddLengthOrStartsWith1AndHaveAnEvenLengthAndThrowException() {
        assertThrows(
            IllegalArgumentException.class,
            () -> Task8.isStringStartsWith0AndHaveAnOddLengthOrStartsWith1AndHaveAnEvenLength(null)
        );
    }
}
