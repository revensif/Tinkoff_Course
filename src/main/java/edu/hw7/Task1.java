package edu.hw7;

import java.util.concurrent.atomic.AtomicInteger;

public final class Task1 {

    private Task1() {
    }

    public static int increaseCounter(int threadsNumber, int loopCounter) {
        if ((threadsNumber <= 0) || (loopCounter <= 0)) {
            return 0;
        }
        AtomicInteger counter = new AtomicInteger(0);
        Thread[] threads = createAndStartThreads(counter, threadsNumber, loopCounter);
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return counter.get();
    }

    private static Thread[] createAndStartThreads(AtomicInteger counter, int threadsNumber, int loopCounter) {
        Thread[] threads = new Thread[threadsNumber];
        for (int i = 0; i < threadsNumber; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < loopCounter; j++) {
                    counter.addAndGet(1);
                }
            });
            threads[i].start();
        }
        return threads;
    }
}
