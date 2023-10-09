package edu.hw1;

public final class Task3 {

    private Task3() {
    }

    public static boolean isNestable(int[] a1, int[] a2) {
        if (a1 == null || a2 == null) {
            return false;
        }
        int min1 = findMin(a1);
        int min2 = findMin(a2);
        int max1 = findMax(a1);
        int max2 = findMax(a2);
        return (min1 > min2) && (max1 < max2);
    }

    private static int findMin(int[] a) {
        int min = a[0];
        for (int num : a) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }

    private static int findMax(int[] a) {
        int max = a[0];
        for (int num : a) {
            if (max < num) {
                max = num;
            }
        }
        return max;
    }
}
