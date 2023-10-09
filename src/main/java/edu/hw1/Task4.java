package edu.hw1;

public final class Task4 {

    private Task4() {
    }

    public static String fixString(String str) {
        char temp;
        if ((str == null) || (str.trim().isEmpty())) {
            return "";
        }
        char[] charArr = str.toCharArray();
        for (int i = 1; i < charArr.length; i += 2) {
            temp = charArr[i];
            charArr[i] = charArr[i - 1];
            charArr[i - 1] = temp;
        }
        return String.valueOf(charArr);
    }
}
