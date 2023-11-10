package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task5Test {

    @ParameterizedTest
    @DisplayName("Task5Test: Correct Input")
    @CsvSource({
        "А123ВЕ777",
        "О777ОО177",
        "А123ВР77"
    })
    void shouldValidateRusLicenseSignsAndReturnTrue(String sign) {
        boolean calculated = Task5.validateRusLicenseSigns(sign);
        assertThat(calculated).isTrue();
    }

    @ParameterizedTest
    @DisplayName("Task5Test: Incorrect Input")
    @CsvSource({
        "123АВЕ777",
        "А123ВГ77",
        "А123ВЕ7777"
    })
    void shouldValidateRusLicenseSignsAndReturnFalse(String sign) {
        boolean calculated = Task5.validateRusLicenseSigns(sign);
        assertThat(calculated).isFalse();
    }
}
