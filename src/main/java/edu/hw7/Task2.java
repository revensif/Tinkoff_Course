package edu.hw7;

import java.math.BigInteger;
import java.util.stream.IntStream;

public final class Task2 {

    private Task2() {
    }

    public static BigInteger parallelFactorial(int number) {
        if (number < 0) {
            return null;
        }
        if ((number == 0) || (number == 1)) {
            return BigInteger.ONE;
        }
        return IntStream
            .rangeClosed(2, number)
            .parallel()
            .mapToObj(BigInteger::valueOf)
            .reduce(BigInteger::multiply)
            .orElseGet(null);
    }
}
