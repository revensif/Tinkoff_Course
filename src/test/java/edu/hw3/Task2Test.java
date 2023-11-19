package edu.hw3;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task2Test {

    @ParameterizedTest
    @DisplayName("Task2Test: Correct Input")
    @MethodSource("clusters")
    void shouldDoClusterizeText(String text, List<String> totalExpected) {
        List<String> totalCalculated = Task2.clusterize(text);
        assertEquals(totalExpected, totalCalculated);
    }

    static Arguments[] clusters() {
        return new Arguments[] {
            Arguments.of("()()()", Arrays.asList("()", "()", "()")),
            Arguments.of("((()))", List.of("((()))")),
            Arguments.of("((()))(())()()(()())", Arrays.asList("((()))", "(())", "()", "()", "(()())")),
        };
    }

    @Test
    @DisplayName("Task2Test: Empty and Null Input")
    void shouldDoClusterizeTextAndReturnEmptyString() {
        assertThat(Task2.clusterize("")).isEqualTo(List.of(""));
        assertThat(Task2.clusterize(null)).isEqualTo(List.of(""));
    }

    @Test
    @DisplayName("Task2Test: Incorrect Input")
    void shouldDoClusterizeTextAndThrowException() {
        assertThrows(IllegalArgumentException.class, () -> Task2.clusterize("12"));
        assertThrows(IllegalArgumentException.class, () -> Task2.clusterize(")("));
        assertThrows(IllegalArgumentException.class, () -> Task2.clusterize("()(("));
        assertThrows(IllegalArgumentException.class, () -> Task2.clusterize("()("));
    }
}
