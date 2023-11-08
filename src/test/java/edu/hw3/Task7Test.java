package edu.hw3;

import java.util.Comparator;
import java.util.TreeMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task7Test {

    @Test
    @DisplayName("Task4Test: Correct Input")
    void shouldDoAddNullToTreeWithoutException() {
        TreeMap<String, String> tree = new TreeMap<>(Comparator.nullsLast(Comparator.naturalOrder()));
        tree.put(null, "test");
        assertThat(tree.containsKey(null)).isTrue();
    }

    @Test
    @DisplayName("Task4Test: Correct Input")
    void shouldDoAddNullToTreeWithoutExceptionComparatorImplementation() {
        TreeMap<String, String> tree = new TreeMap<>((o1, o2) -> {
            if (o1 == null || o2 == null) {
                return 0;
            }
            return o1.compareTo(o2);
        });
        tree.put(null, "test");
        assertThat(tree.containsKey(null)).isTrue();
    }
}
