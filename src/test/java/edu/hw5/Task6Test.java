package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task6Test {

    @ParameterizedTest
    @DisplayName("Task6Test: Correct Input")
    @CsvSource({
        "abc, achfdbaabgabcaabg",
        "baaaaag, achfdbaabgabcaabg",
        "aaaaaa, achfdbaabgabcaabg"
    })
    void shouldCheckIsStringASubsequenceOfAnotherStringAndReturnTrue(String subsequence, String string) {
        boolean calculated = Task6.isStringASubsequenceOfAnotherString(subsequence, string);
        assertThat(calculated).isTrue();
    }

    @ParameterizedTest
    @DisplayName("Task6Test: Incorrect Input")
    @CsvSource({
        "aсbggb, achfdbaabgabcaabg",
        "abcc, abc",
        "aaaaaaa, achfdbaabgabcaabg"
    })
    void shouldCheckIsStringASubsequenceOfAnotherStringAndReturnFalse(String sign) {
        boolean calculated = Task5.validateRusLicenseSigns(sign);
        assertThat(calculated).isFalse();
    }


    @Test
    @DisplayName("Task6Test: Incorrect Input")
    void shouldCheckIsStringASubsequenceOfAnotherStringAndThrowException() {
        assertThrows(IllegalArgumentException.class, () -> Task6.isStringASubsequenceOfAnotherString(null, null));
        assertThrows(IllegalArgumentException.class, () -> Task6.isStringASubsequenceOfAnotherString("test", null));
        assertThrows(IllegalArgumentException.class, () -> Task6.isStringASubsequenceOfAnotherString(null, "test"));
    }
}
