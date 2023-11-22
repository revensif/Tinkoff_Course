package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.math.BigInteger;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {

    @ParameterizedTest
    @DisplayName("parallelFactorial() Test : Correct Input")
    @CsvSource({
        "0, 1", "1, 1", "5, 120", "9, 362880",
        "21, 51090942171709440000",
        "27, 10888869450418352160768000000"
    })
    void shouldCalculateFactorial(int number, BigInteger expected) {
        BigInteger calculated = Task2.parallelFactorial(number);
        assertThat(calculated).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("parallelFactorial() Test : Incorrect Input")
    @CsvSource({
        "-12", "-1928190", "-1"
    })
    void shouldCalculateFactorialAndReturnNull(int number) {
        assertThat(Task2.parallelFactorial(number)).isNull();
    }
}
