package edu.hw1;

import java.util.Arrays;

public final class Task6 {
    private static final int KAPREKAR = 6174;
    private static final int NUM1000 = 1000;
    private static final int NUM10000 = 10000;

    private Task6() {
    }

    public static int countK(int num) {
        int count = 0;
        if ((num <= NUM1000) || (num >= NUM10000)) {
            return -1;
        }
        if (isRepeated(num)) {
            return -1;
        }
        return recursiveCount(num, count);
    }

    private static int recursiveCount(int num, int count) {
        if (num != KAPREKAR) {
            int[] nums = convert(num);
            int sum = nums[0] - nums[1];
            return recursiveCount(sum, count + 1);
        }
        return count;
    }

    private static int[] convert(int num) {
        int n = num;
        if (n < NUM1000) {
            n *= Task2.TEN;
        }
        char[] charArr = ("" + n).toCharArray();
        Arrays.sort(charArr);
        String ascend = new String(charArr);
        String descend = new StringBuilder(ascend).reverse().toString();
        int asc = Integer.parseInt(ascend);
        int desc = Integer.parseInt(descend);
        return new int[] {Math.max(asc, desc), Math.min(asc, desc)};
    }

    private static boolean isRepeated(int num) {
        char[] digits = ("" + num).toCharArray();
        for (int i = 0; i < digits.length - 1; i++) {
            if (digits[i] != digits[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
