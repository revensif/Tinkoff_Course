package edu.hw8.Task2;

public final class FibCalculator {

    private FibCalculator() {
    }

    public static int calculateFib(int number) {
        int sign = 1;
        if (number < 0) {
            sign *= -1;
        }
        int num = sign * number;
        if ((num == 0) || (num == 1)) {
            return sign * num;
        }
        int n1 = 0;
        int n2 = 1;
        int sum = 0;
        for (int i = 2; i <= num; i++) {
            sum = n1 + n2;
            n1 = n2;
            n2 = sum;
        }
        return sign * sum;
    }
}
