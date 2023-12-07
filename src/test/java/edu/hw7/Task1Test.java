package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {

    @ParameterizedTest
    @DisplayName("increaseCounter() Test : Correct Input")
    @CsvSource({
        "2, 20, 40", "10, 91, 910", "229, 132, 30228"
    })
    void shouldIncreaseCounter(int threadsNumber, int loopCounter, int expected) {
        int calculated = Task1.increaseCounter(threadsNumber, loopCounter);
        assertThat(calculated).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("increaseCounter() Test : Correct Input")
    @CsvSource({
        "-10, 20, 0", "0, 123, 0", "123, -10, 0", "1231, 0, 0"
    })
    void shouldIncreaseCounterAndReturn0(int threadsNumber, int loopCounter, int expected) {
        int calculated = Task1.increaseCounter(threadsNumber, loopCounter);
        assertThat(calculated).isEqualTo(expected);
    }
}
