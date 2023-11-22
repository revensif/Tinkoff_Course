package edu.hw7.Task4Simulation;

import edu.hw7.Task4;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task4ResultCalculation {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String MESSAGE = "Simulation result for {} POINTS";
    private static final int POINT_NUMBER = 3;
    private static final int SIMULATION_NUMBER = 100;
    private static final int LINE_LENGTH = 50;
    private static final int CONVERT = 1_000_000;
    private static final int[] POINTS = new int[] {10_000, 100_000, 1_000_000, 10_000_000};
    private static final int[] THREADS = new int[] {2, 4, 6, 8};

    private Task4ResultCalculation() {
    }

    public static void calculateError() {
        for (int pointNumber : POINTS) {
            double result = 0;
            for (int i = 0; i < SIMULATION_NUMBER; i++) {
                result += Task4.multipleThreadsPICalculation(pointNumber, THREADS[1]);
            }
            result /= SIMULATION_NUMBER;
            LOGGER.info(MESSAGE, pointNumber);
            LOGGER.info("Average Error For 4 Threads = {}", Math.abs(result - Math.PI));
            LOGGER.info("#".repeat(LINE_LENGTH));
        }
    }

    public static void calculateAverageTime() {
        LOGGER.info(MESSAGE, POINTS[POINT_NUMBER]);
        double averageTime = 0;
        for (int i = 0; i < SIMULATION_NUMBER; i++) {
            double startTime = System.nanoTime();
            Task4.singleThreadPICalculation(POINTS[POINT_NUMBER]);
            double endTime = System.nanoTime();
            averageTime += (endTime - startTime) / CONVERT;
        }
        LOGGER.info("Average Time For Single Thread = {} ms", averageTime / SIMULATION_NUMBER);
        LOGGER.info("#".repeat(LINE_LENGTH));
        for (int thread : THREADS) {
            averageTime = 0;
            for (int i = 0; i < SIMULATION_NUMBER; i++) {
                double startTime = System.nanoTime();
                Task4.multipleThreadsPICalculation(POINTS[POINT_NUMBER], thread);
                double endTime = System.nanoTime();
                averageTime += (endTime - startTime) / CONVERT;
            }
            LOGGER.info("Average Time For {} Threads = {} ms", thread, averageTime / SIMULATION_NUMBER);
            LOGGER.info("#".repeat(LINE_LENGTH));
        }
    }
}
