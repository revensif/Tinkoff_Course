package edu.hw3;

import java.util.TreeMap;

public final class Task4 {
    private static final TreeMap<Integer, String> MAP = new TreeMap<>();
    private static final int MAX_NUMBER = 3999;
    private static final int ONE_THOUSAND = 1000;
    private static final int NINE_HUNDRED = 900;
    private static final int FIVE_HUNDRED = 500;
    private static final int FOUR_HUNDRED = 400;
    private static final int ONE_HUNDRED = 100;
    private static final int NINETY = 90;
    private static final int FIFTY = 50;
    private static final int FORTY = 40;
    private static final int TEN = 10;
    private static final int NINE = 9;
    private static final int FIVE = 5;
    private static final int FOUR = 4;
    private static final int ONE = 1;

    static {
        MAP.put(ONE_THOUSAND, "M");
        MAP.put(NINE_HUNDRED, "CM");
        MAP.put(FIVE_HUNDRED, "D");
        MAP.put(FOUR_HUNDRED, "CD");
        MAP.put(ONE_HUNDRED, "C");
        MAP.put(NINETY, "XC");
        MAP.put(FIFTY, "L");
        MAP.put(FORTY, "XL");
        MAP.put(TEN, "X");
        MAP.put(NINE, "IX");
        MAP.put(FIVE, "V");
        MAP.put(FOUR, "IV");
        MAP.put(ONE, "I");
    }

    private Task4() {
    }

    public static String convertToRoman(int number) {
        if ((number < ONE) || (number > MAX_NUMBER)) {
            throw new IllegalArgumentException("The number must be in the range [1:3999]");
        }
        StringBuilder stringBuilder = new StringBuilder();
        int l = number;
        while (l != 0) {
            int maxNum = MAP.floorKey(l);
            stringBuilder.append(MAP.get(maxNum));
            l -= maxNum;
        }
        return String.valueOf(stringBuilder);
    }
}
