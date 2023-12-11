package edu.hw10.Task2;

public class RecursiveFibCalculator implements FibCalculator {

    @Override
    public long fib(int number) {
        return calculateFib(number);
    }

    @Override
    public long fibNoCache(int number) {
        return calculateFib(number);
    }

    private int calculateFib(int number) {
        if (number <= 1) {
            return number;
        }
        return calculateFib(number - 1) + calculateFib(number - 2);
    }
}
