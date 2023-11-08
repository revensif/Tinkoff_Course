package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task4Test {

    @ParameterizedTest
    @DisplayName("Task3Test: Correct Input")
    @CsvSource({
        "2, II",
        "12, XII",
        "16, XVI",
        "194, CXCIV",
        "2383, MMCCCLXXXIII"
    })
    void shouldDoConvertToRoman(int number, String totalExpected) {
        String totalCalculated = Task4.convertToRoman(number);
        assertEquals(totalExpected, totalCalculated);
    }

    @Test
    @DisplayName("Task4Test: Incorrect Input")
    void shouldDoConvertToRomanAndThrowException() {
        assertThrows(IllegalArgumentException.class, () -> Task4.convertToRoman(4000));
        assertThrows(IllegalArgumentException.class, () -> Task4.convertToRoman(0));
    }
}
