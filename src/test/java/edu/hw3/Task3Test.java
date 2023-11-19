package edu.hw3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task3Test {

    @ParameterizedTest
    @DisplayName("Task3Test: Correct Input")
    @MethodSource("lists")
    <T> void shouldDoFreqDict(List<T> list, Map<T, Integer> totalExpected) {
        Map<T, Integer> totalCalculated = Task3.freqDict(list);
        assertEquals(totalExpected, totalCalculated);
    }

    static Arguments[] lists() {
        return new Arguments[] {
            Arguments.of(Arrays.asList("a", "bb", "a", "bb"), Map.of("bb", 2, "a", 2)),
            Arguments.of(Arrays.asList("this", "and", "that", "and"), Map.of("that", 1, "and", 2, "this", 1)),
            Arguments.of(Arrays.asList("код", "код", "код", "bug"), Map.of("код", 3, "bug", 1)),
            Arguments.of(Arrays.asList(1, 1, 2, 2), Map.of(1, 2, 2, 2)),
        };
    }

    @Test
    @DisplayName("Task3Test: Incorrect Input")
    void shouldDoFreqDictAndThrowException() {
        assertThrows(IllegalArgumentException.class, () -> Task3.freqDict(new ArrayList<>()));
        assertThrows(IllegalArgumentException.class, () -> Task3.freqDict(null));
    }
}
