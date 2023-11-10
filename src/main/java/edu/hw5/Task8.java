package edu.hw5;

import static edu.hw5.Task7.MESSAGE;

public final class Task8 {
    private static final String REGEX1 = "([01]{2})*[01]";
    private static final String REGEX2 = "^0([01]{2})*[01]|^1([01]{2})*";
    private static final String REGEX3 = "(1*(01*){3})+";
    private static final String REGEX4 = "1?|0[01]*|10[01]*|110[01]*|111[01]+";
    private static final String REGEX5_1 = "([01]1)*[01]?"; //Счет с нуля
    private static final String REGEX5_2 = "(1[01])*1?"; //Счет с единицы
    private static final String REGEX6 = "0+1?0+|10{2,}|0{2,}1";
    private static final String REGEX7 = "[01]?(0+1?0?)*";

    private Task8() {
    }

    public static boolean isStringHaveAnOddLength(String string) {
        if (string == null) {
            throw new IllegalArgumentException(MESSAGE);
        }
        return string.matches(REGEX1);
    }

    public static boolean isStringStartsWith0AndHaveAnOddLengthOrStartsWith1AndHaveAnEvenLength(String string) {
        if (string == null) {
            throw new IllegalArgumentException(MESSAGE);
        }
        return string.matches(REGEX2);
    }

    public static boolean isStringContainsZerosWhoseNumberIsAMultipleOf3(String string) {
        if (string == null) {
            throw new IllegalArgumentException(MESSAGE);
        }
        return string.matches(REGEX3);
    }

    public static boolean isStringContainsAnyStringOtherThan11or111(String string) {
        if (string == null) {
            throw new IllegalArgumentException(MESSAGE);
        }
        return string.matches(REGEX4);
    }

    public static boolean isEachOddCharacterInStringIs1StartsWith0(String string) {
        if (string == null) {
            throw new IllegalArgumentException(MESSAGE);
        }
        return string.matches(REGEX5_1);
    }

    public static boolean isEachOddCharacterInStringIs1StartsWith1(String string) {
        if (string == null) {
            throw new IllegalArgumentException(MESSAGE);
        }
        return string.matches(REGEX5_2);
    }

    public static boolean isStringContainsAtLeastTwo0AndAtMostOne1(String string) {
        if (string == null) {
            throw new IllegalArgumentException(MESSAGE);
        }
        return string.matches(REGEX6);
    }

    public static boolean isStringNotContainsConsecutive1s(String string) {
        if (string == null) {
            throw new IllegalArgumentException(MESSAGE);
        }
        return string.matches(REGEX7);
    }
}
