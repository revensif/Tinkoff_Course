package edu.hw5.Task8Test;

import edu.hw5.Task8;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FifthSecondRegExTest {

    @ParameterizedTest
    @DisplayName("FifthSecondRegExTest : Correct Input")
    @CsvSource({
        "''", "1", "10", "11", "101", "111", "1010", "1110", "10101"
    })
    void shouldCheckIsEachOddCharacterInStringIs1StartsWith1AndReturnTrue(String string) {
        boolean calculated = Task8.isEachOddCharacterInStringIs1StartsWith1(string);
        assertThat(calculated).isTrue();
    }

    @ParameterizedTest
    @DisplayName("FifthSecondRegExTest : Incorrect Input")
    @CsvSource({
        "0", "01", "00", "001", "100", "0001", "0100", "0110", "1001", "10100", "11010"
    })
    void shouldCheckIsEachOddCharacterInStringIs1StartsWith1AndReturnFalse(String string) {
        boolean calculated = Task8.isEachOddCharacterInStringIs1StartsWith1(string);
        assertThat(calculated).isFalse();
    }

    @Test
    @DisplayName("FifthSecondRegExTest : Incorrect Input")
    void shouldCheckIsEachOddCharacterInStringIs1StartsWith1AndThrowException() {
        assertThrows(
            IllegalArgumentException.class,
            () -> Task8.isEachOddCharacterInStringIs1StartsWith1(null)
        );
    }
}
