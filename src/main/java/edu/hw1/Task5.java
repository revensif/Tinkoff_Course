package edu.hw1;

public final class Task5 {
    private final static int TEN = 10;

    private Task5() {
    }

    //Для четных чиcел: 11211230 -> 2333 -> 56 -> 11 -> true
    //Для нечетных чисел: 37956 -> 10146 -> 156 -> 66 -> true
    //Считаем, что к последней цифре прибавляется 0
    public static boolean isPalindromeDescendant(int num) {
        int n = Math.abs(num);
        while (n >= TEN) {
            char[] charArr = ("" + n).toCharArray();
            if (isPalindrome(charArr)) {
                return true;
            }
            StringBuilder res = new StringBuilder();
            for (int i = 1; i < charArr.length; i += 2) {
                int a = Character.getNumericValue(charArr[i]);
                int b = Character.getNumericValue(charArr[i - 1]);
                res.append(a + b);
            }
            if (charArr.length % 2 == 1) {
                res.append(charArr[charArr.length - 1]);
            }
            n = Integer.parseInt(String.valueOf(res));
        }
        return false;
    }

    private static boolean isPalindrome(char[] charArr) {
        for (int i = 0; i < charArr.length / 2; i++) {
            if (charArr[i] != charArr[charArr.length - i - 1]) {
                return false;
            }
        }
        return true;
    }
}
