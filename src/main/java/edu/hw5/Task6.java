package edu.hw5;

public final class Task6 {
    private static final String ALL_SYMBOLS = ".*";
    private static final int LENGTH = 7;

    private Task6() {
    }

    public static boolean isStringASubsequenceOfAnotherString(String subsequence, String string) {
        if ((string == null) || (subsequence == null)) {
            throw new IllegalArgumentException("subsequence is null or string is null");
        }
        StringBuilder stringBuilder = new StringBuilder(subsequence.length() * LENGTH + 2);
        char[] chars = subsequence.toCharArray();
        stringBuilder.append(ALL_SYMBOLS);
        for (char c : chars) {
            String screening = "\\Q" + c + "\\E";
            stringBuilder.append(screening).append(ALL_SYMBOLS);
        }
        return string.matches(String.valueOf(stringBuilder));
    }
}
