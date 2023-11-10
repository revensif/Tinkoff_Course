package edu.hw5.Task8Test;

import edu.hw5.Task8;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SixthRegExTest {

    @ParameterizedTest
    @DisplayName("SixthRegExTest : Correct Input")
    @CsvSource({
        "00", "010", "00100", "100", "001", "01000"
    })
    void shouldCheckIsStringContainsAtLeastTwo0AndAtMostOne1AndReturnTrue(String string) {
        boolean calculated = Task8.isStringContainsAtLeastTwo0AndAtMostOne1(string);
        assertThat(calculated).isTrue();
    }

    @ParameterizedTest
    @DisplayName("SixthRegExTest : Incorrect Input")
    @CsvSource({
        "''", "0", "01", "11", "110", "111", "0011", "1001", "01001"
    })
    void shouldCheckIsStringContainsAtLeastTwo0AndAtMostOne1AndReturnFalse(String string) {
        boolean calculated = Task8.isStringContainsAtLeastTwo0AndAtMostOne1(string);
        assertThat(calculated).isFalse();
    }

    @Test
    @DisplayName("SixthRegExTest : Incorrect Input")
    void shouldCheckIsStringContainsAtLeastTwo0AndAtMostOne1AndThrowException() {
        assertThrows(
            IllegalArgumentException.class,
            () -> Task8.isStringContainsAtLeastTwo0AndAtMostOne1(null)
        );
    }
}
