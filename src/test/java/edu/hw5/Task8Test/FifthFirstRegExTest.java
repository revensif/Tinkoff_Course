package edu.hw5.Task8Test;

import edu.hw5.Task8;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FifthFirstRegExTest {

    @ParameterizedTest
    @DisplayName("FifthFirstRegExTest : Correct Input")
    @CsvSource({
        "''", "0", "1", "01", "11", "010", "011", "110", "0101", "0111"
    })
    void shouldCheckIsEachOddCharacterInStringIs1StartsWith0AndReturnTrue(String string) {
        boolean calculated = Task8.isEachOddCharacterInStringIs1StartsWith0(string);
        assertThat(calculated).isTrue();
    }

    @ParameterizedTest
    @DisplayName("FifthFirstRegExTest : Incorrect Input")
    @CsvSource({
        "00", "10", "001", "100", "101", "0001", "0100", "0110"
    })
    void shouldCheckIsEachOddCharacterInStringIs1StartsWith0AndReturnFalse(String string) {
        boolean calculated = Task8.isEachOddCharacterInStringIs1StartsWith0(string);
        assertThat(calculated).isFalse();
    }

    @Test
    @DisplayName("FifthFirstRegExTest : Incorrect Input")
    void shouldCheckIsEachOddCharacterInStringIs1StartsWith0AndThrowException() {
        assertThrows(
            IllegalArgumentException.class,
            () -> Task8.isEachOddCharacterInStringIs1StartsWith0(null)
        );
    }
}
