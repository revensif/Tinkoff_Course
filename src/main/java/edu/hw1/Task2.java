package edu.hw1;

public final class Task2 {
    private static final int TEN = 10;

    private Task2() {
    }

    public static int countDigits(int num) {
        int count = 1;
        int n = Math.abs(num);
        while (n >= TEN) {
            n /= TEN;
            count++;
        }
        return count;
    }
}
