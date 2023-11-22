package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {
    private static final int THREADS = 4;
    private static final int MAX_POINTS = 10_000_000;
    private static final double MAX_ERROR = 0.01;

    @Test
    @DisplayName("singleThreadPICalculation() Test")
    void shouldCalculatePIWithSingleThreadAndErrorShouldBeSmallerThanMaxError() {
        double calculated = Task4.singleThreadPICalculation(MAX_POINTS);
        assertThat(calculated - Math.PI < MAX_ERROR).isTrue();
    }

    @Test
    @DisplayName("multipleThreadsPICalculation() Test")
    void shouldCalculatePIWithMultipleThreadsAndErrorShouldBeSmallerThanMaxError() {
        double calculated = Task4.multipleThreadsPICalculation(MAX_POINTS, THREADS);
        assertThat(calculated - Math.PI < MAX_ERROR).isTrue();
    }
}
