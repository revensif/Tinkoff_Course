package edu.hw5.Task8Test;

import edu.hw5.Task8;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FourthRegExTest {

    @ParameterizedTest
    @DisplayName("FourthRegExTest : Correct Input")
    @CsvSource({
        "''", "0", "1", "01", "10", "000", "1000", "01101", "0001101100", "110010111"
    })
    void shouldCheckIsStringContainsAnyStringOtherThan11or111AndReturnTrue(String string) {
        boolean calculated = Task8.isStringContainsAnyStringOtherThan11or111(string);
        assertThat(calculated).isTrue();
    }

    @ParameterizedTest
    @DisplayName("FourthRegExTest : Incorrect Input")
    @CsvSource({
        "11", "111"
    })
    void shouldCheckIsStringContainsAnyStringOtherThan11or111AndReturnFalse(String string) {
        boolean calculated = Task8.isStringContainsAnyStringOtherThan11or111(string);
        assertThat(calculated).isFalse();
    }

    @Test
    @DisplayName("FourthRegExTest : Incorrect Input")
    void shouldCheckIsStringContainsAnyStringOtherThan11or111AndThrowException() {
        assertThrows(
            IllegalArgumentException.class,
            () -> Task8.isStringContainsAnyStringOtherThan11or111(null)
        );
    }
}
