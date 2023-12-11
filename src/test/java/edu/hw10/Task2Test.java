package edu.hw10;

import edu.hw10.Task2.CacheProxy;
import edu.hw10.Task2.FibCalculator;
import edu.hw10.Task2.RecursiveFibCalculator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    private static final Path PATH = Paths.get("src/test/java/edu/hw10/DirForCache");

    @Test
    @DisplayName("CacheProxy Test : Comparison of Results")
    void shouldCalculateFibAndToBeEqualToFibCalculatorResult() {
        Path cachePath = Paths.get(PATH + "/test1.txt");
        deleteFile(cachePath);
        FibCalculator calculator = new RecursiveFibCalculator();
        FibCalculator proxy =
            CacheProxy.create(calculator, RecursiveFibCalculator.class, cachePath);
        long expected = calculator.fib(5);
        long actual = proxy.fib(5);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("CacheProxy Test : Cache Check")
    void shouldCalculateFibAndToBeEqualToCacheValue() {
        Path cachePath = Paths.get(PATH + "/test2.txt");
        deleteFile(cachePath);
        FibCalculator calculator = new RecursiveFibCalculator();
        FibCalculator proxy =
            CacheProxy.create(calculator, RecursiveFibCalculator.class, cachePath);
        long expected = proxy.fib(7);
        long actual = proxy.fib(7);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("CacheProxy Test : File Addition Check")
    void shouldCalculateFibAndCreateFileForCache() {
        Path cachePath = Paths.get(PATH + "/test3.txt");
        deleteFile(cachePath);
        FibCalculator calculator = new RecursiveFibCalculator();
        FibCalculator proxy = CacheProxy.create(calculator, RecursiveFibCalculator.class, cachePath);
        proxy.fib(6);
        assertThat(Files.exists(cachePath)).isTrue();
        proxy.fib(9);
        proxy.fib(3);
        String[] values = new String[] {"fib[6] = 8", "fib[9] = 34", "fib[3] = 2"};
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(cachePath.toFile()))) {
            for (String value : values) {
                assertThat(bufferedReader.readLine()).isEqualTo(value);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("CacheProxy Test : persistent = false")
    void shouldCalculateFibAndCreateNothing() {
        Path cachePath = Paths.get(PATH + "/test4.txt");
        deleteFile(cachePath);
        FibCalculator calculator = new RecursiveFibCalculator();
        FibCalculator proxy = CacheProxy.create(calculator, RecursiveFibCalculator.class, cachePath);
        proxy.fibNoCache(6);
        assertThat(Files.exists(cachePath)).isFalse();
        proxy.fibNoCache(9);
        proxy.fibNoCache(3);
        assertThat(Files.exists(cachePath)).isFalse();
    }

    private void deleteFile(Path path) {
        if (Files.exists(path)) {
            try {
                Files.delete(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
