package edu.hw2.Task2Test;

import edu.hw2.Task2.Rectangle;
import edu.hw2.Task2.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    @ParameterizedTest
    @DisplayName("Task2Test: Correct Input")
    @MethodSource("rectangles")
    void testTask2(Rectangle rect) {
        rect = rect.setWidth(20);
        rect = rect.setHeight(10);
        assertThat(rect.area()).isEqualTo(200.0);
    }

    static Arguments[] rectangles() {
        return new Arguments[] {
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        };
    }
}
