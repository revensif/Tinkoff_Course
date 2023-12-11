package edu.hw8.Task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FixedThreadPool implements ThreadPool {
    private static final String ERROR_MESSAGE = "An error has occurred: {}";
    private static final Logger LOGGER = LogManager.getLogger();
    private Thread[] threads;
    private final BlockingQueue<Runnable> runnableBlockingQueue = new LinkedBlockingQueue<>();

    public FixedThreadPool(int threads) {
        this.threads = new Thread[threads];
    }

    @Override
    public void start() {
        if (runnableBlockingQueue.size() > threads.length) {
            threads = new Thread[runnableBlockingQueue.size()];
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                try {
                    Runnable runnable = runnableBlockingQueue.take();
                    runnable.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    LOGGER.error(ERROR_MESSAGE, e.getMessage());
                }
            });
            threads[i].start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        try {
            runnableBlockingQueue.put(runnable);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.error(ERROR_MESSAGE, e.getMessage());
        }
    }

    @Override
    public void close() throws Exception {
        for (Thread thread : threads) {
            thread.join();
        }
    }

    public static FixedThreadPool create(int threads) {
        if (threads <= 0) {
            return null;
        }
        return new FixedThreadPool(threads);
    }
}
