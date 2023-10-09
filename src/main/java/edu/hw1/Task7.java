package edu.hw1;

public final class Task7 {
    private Task7() {
    }

    public static int rotateLeft(int n, int shift) {
        int res = 0;
        char temp;
        if (n == 0) {
            return 0;
        }
        int num = Math.abs(n);
        if (shift < 0) {
            return rotateRight(Math.abs(num), -shift);
        }
        char[] charArr = convertToBinary(num);
        for (int i = 0; i < shift % charArr.length; i++) {
            temp = charArr[0];
            for (int j = 0; j < charArr.length - 1; j++) {
                charArr[j] = charArr[j + 1];
            }
            charArr[charArr.length - 1] = temp;
        }
        for (char bit : charArr) {
            res = res * 2 + Character.getNumericValue(bit);
        }
        return res;
    }

    public static int rotateRight(int n, int shift) {
        int res = 0;
        char temp;
        if (n == 0) {
            return 0;
        }
        int num = Math.abs(n);
        if (shift < 0) {
            return rotateLeft(num, -shift);
        }
        char[] charArr = convertToBinary(num);
        for (int i = 0; i < shift % charArr.length; i++) {
            temp = charArr[charArr.length - 1];
            for (int j = charArr.length - 1; j > 0; j--) {
                charArr[j] = charArr[j - 1];
            }
            charArr[0] = temp;
        }
        for (char bit : charArr) {
            res = res * 2 + Character.getNumericValue(bit);
        }
        return res;
    }

    private static char[] convertToBinary(int num) {
        int n = num;
        StringBuilder str = new StringBuilder();
        while (n != 1) {
            str.append(n % 2);
            n /= 2;
        }
        str.append(1);
        return str.reverse().toString().toCharArray();
    }
}
