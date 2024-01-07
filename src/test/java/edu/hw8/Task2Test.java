package edu.hw8;

import edu.hw8.Task2.FibCalculator;
import edu.hw8.Task2.FixedThreadPool;
import edu.hw8.Task2.ThreadPool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {

    @Test
    @DisplayName("calculateFib Test")
    void calculateFib() {
        int[] expected = {0, 2, 8, 21, 55};
        int[] index = {0, 3, 6, 8, 10};
        int[] actual = new int[expected.length];
        try (ThreadPool threadPool = FixedThreadPool.create(expected.length)) {
            if (threadPool == null) {
                return;
            }
            for (int i = 0; i < expected.length; i++) {
                int finalIndex = index[i];
                int finalI = i;
                threadPool.execute(() -> {
                    int calculated = FibCalculator.calculateFib(finalIndex);
                    actual[finalI] = calculated;
                });
            }
            threadPool.start();
            threadPool.close();
            assertThat(actual).containsExactly(expected);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("creation Test")
    void shouldCreateFixedThreadPoolAndReturnNull() {
        assertThat(FixedThreadPool.create(0)).isNull();
    }
}
