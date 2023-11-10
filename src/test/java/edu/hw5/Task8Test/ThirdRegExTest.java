package edu.hw5.Task8Test;

import edu.hw5.Task8;
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
        "000", "1000", "0001101100", "110010111"
    })
    void shouldCheckIsStringContainsZerosWhoseNumberIsAMultipleOf3AndReturnTrue(String string) {
        boolean calculated = Task8.isStringContainsZerosWhoseNumberIsAMultipleOf3(string);
        assertThat(calculated).isTrue();
    }

    @ParameterizedTest
    @DisplayName("ThirdRegExTest : Incorrect Input")
    @CsvSource({
        "''", "1", "0011", "1010110110"
    })
    void shouldCheckIsStringContainsZerosWhoseNumberIsAMultipleOf3AndReturnFalse(String string) {
        boolean calculated = Task8.isStringContainsZerosWhoseNumberIsAMultipleOf3(string);
        assertThat(calculated).isFalse();
    }

    @Test
    @DisplayName("ThirdRegExTest : Incorrect Input")
    void shouldCheckIsStringContainsZerosWhoseNumberIsAMultipleOf3AndThrowException() {
        assertThrows(
            IllegalArgumentException.class,
            () -> Task8.isStringContainsZerosWhoseNumberIsAMultipleOf3(null)
        );
    }
}
