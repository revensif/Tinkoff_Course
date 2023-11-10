package edu.hw5.Task8Test;

import edu.hw5.Task8;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SeventhRegExTest {
    @ParameterizedTest
    @DisplayName("SeventhRegExTest : Correct Input")
    @CsvSource({
        "''", "0", "1", "00", "01", "10", "010", "0010101", "00100", "100", "001", "01001"
    })
    void shouldCheckIsStringNotContainsConsecutive1sAndReturnTrue(String string) {
        boolean calculated = Task8.isStringNotContainsConsecutive1s(string);
        assertThat(calculated).isTrue();
    }

    @ParameterizedTest
    @DisplayName("SeventhRegExTest : Incorrect Input")
    @CsvSource({
        "11", "011", "10011", "0001100", "111", "1111", "0001001101"
    })
    void shouldCheckIsStringNotContainsConsecutive1sAndReturnFalse(String string) {
        boolean calculated = Task8.isStringNotContainsConsecutive1s(string);
        assertThat(calculated).isFalse();
    }

    @Test
    @DisplayName("SeventhRegExTest : Incorrect Input")
    void shouldCheckIsStringNotContainsConsecutive1sAndThrowException() {
        assertThrows(
            IllegalArgumentException.class,
            () -> Task8.isStringNotContainsConsecutive1s(null)
        );
    }
}
