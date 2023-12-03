package edu.hw9.Task1;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StatsCollector {
    private final Map<String, Statistic> statisticMap = new ConcurrentHashMap<>();
    private final ExecutorService executorService;

    public StatsCollector(int threads) {
        executorService = Executors.newFixedThreadPool(threads);
    }

    public List<Statistic> stats() {
        return statisticMap.values()
            .stream().toList();
    }

    public void push(String metricName, double[] values) {
        Runnable runnable = (() -> {
            double sum = Arrays.stream(values)
                .sum();
            double max = Arrays.stream(values)
                .max().orElseGet(null);
            double min = Arrays.stream(values)
                .min().orElseGet(null);
            statisticMap.put(metricName, new Statistic(metricName, sum, sum / values.length, max, min));
        });
        executorService.submit(runnable);
    }
}
