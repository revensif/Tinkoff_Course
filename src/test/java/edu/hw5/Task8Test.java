package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task8Test {

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
