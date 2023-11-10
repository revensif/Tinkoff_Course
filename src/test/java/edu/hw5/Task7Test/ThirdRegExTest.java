package edu.hw5.Task7Test;

import edu.hw5.Task7;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ThirdRegExTest {

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
