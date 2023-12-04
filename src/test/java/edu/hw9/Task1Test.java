package edu.hw9;

import edu.hw9.Task1.Statistic;
import edu.hw9.Task1.StatsCollector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {

    @Test
    @DisplayName("statsCollector Test")
    void shouldCollectAllStatistic() {
        StatsCollector collector = new StatsCollector(4);
        Thread[] threads = createThreads(collector);
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<Statistic> statisticList = collector.stats();
        assertThat(statisticList.size()).isEqualTo(4);
        assertThat(statisticList.get(0).average()).isEqualTo(2.0);
        assertThat(statisticList.get(1).max()).isEqualTo(4.0);
        assertThat(statisticList.get(2).min()).isEqualTo(0.05);
        assertThat(statisticList.get(3).sum()).isEqualTo(2.0);
    }

    private Thread[] createThreads(StatsCollector collector) {
        Thread thread1 = new Thread(() -> collector.push("metric1", new double[] {1.2, 2.8, 2.0}));
        Thread thread2 = new Thread(() -> collector.push("metric2", new double[] {1.2, 2.8, 2.0, 4.0}));
        Thread thread3 = new Thread(() -> collector.push("metric3", new double[] {0.1, 0.05, 1.4, 5.1, 0.3}));
        Thread thread4 = new Thread(() -> collector.push("metric4", new double[] {1.9, 0.1}));
        return new Thread[] {thread1, thread2, thread3, thread4};
    }
}
