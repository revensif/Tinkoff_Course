package edu.hw7;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task4 {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Random RANDOM = new Random();
    private static final int CONSTANT_IN_FORMULA = 4;
    private static final int SIDE = 2;
    private static final int RADIUS = 1;
    private static AtomicInteger threadTotalCount;
    private static AtomicInteger threadCircleCount;

    private Task4() {
    }

    public static double singleThreadPICalculation(int number) {
        int circleCount = 0;
        for (int i = 0; i < number; i++) {
            double x = RANDOM.nextDouble(0, SIDE);
            double y = RANDOM.nextDouble(0, SIDE);
            if (isPointInCircle(x, y)) {
                circleCount++;
            }
        }
        return (double) CONSTANT_IN_FORMULA * circleCount / number;
    }

    public static double multipleThreadsPICalculation(int number, int threadsNumber) {
        threadTotalCount = new AtomicInteger(0);
        threadCircleCount = new AtomicInteger(0);
        Thread[] threads = createThreads(number, threadsNumber);
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            LOGGER.info("An error has occured: ", e);
        }
        return (double) CONSTANT_IN_FORMULA * threadCircleCount.get() / threadTotalCount.get();
    }

    private static Thread[] createThreads(int number, int threadsNumber) {
        Thread[] threads = new Thread[threadsNumber];
        for (int i = 0; i < threadsNumber; i++) {
            threads[i] = new Thread(() -> {
                int circleCount = 0;
                int length = number / threadsNumber;
                for (int j = 0; j < length; j++) {
                    double x = ThreadLocalRandom.current().nextDouble(0, SIDE);
                    double y = ThreadLocalRandom.current().nextDouble(0, SIDE);
                    if (isPointInCircle(x, y)) {
                        circleCount++;
                    }
                }
                threadCircleCount.addAndGet(circleCount);
                threadTotalCount.addAndGet(length);
            });
            threads[i].start();
        }
        return threads;
    }

    private static boolean isPointInCircle(double x, double y) {
        return ((Math.pow(x - RADIUS, 2) + Math.pow(y - RADIUS, 2)) <= Math.pow(RADIUS, 2));
    }
}
