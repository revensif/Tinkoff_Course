package edu.hw5;

public final class Task7 {
    private static final String REGEX1 = "[01]{2}0[01]*";
    private static final String REGEX2 = "1([01]*1)?|0([01]*0)?";
    private static final String REGEX3 = "[01]{1,3}";
    static final String MESSAGE = "string is null";

    private Task7() {
    }

    public static boolean isStringHasMoreThan3SymbolsAndTheThirdSymbolIsZero(String string) {
        if (string == null) {
            throw new IllegalArgumentException(MESSAGE);
        }
        return string.matches(REGEX1);
    }

    public static boolean isStringStartsAndEndsWithTheSameSymbol(String string) {
        if (string == null) {
            throw new IllegalArgumentException(MESSAGE);
        }
        return string.matches(REGEX2);
    }

    public static boolean isStringLengthIsNotLessThan1AndNotMoreThan3(String string) {
        if (string == null) {
            throw new IllegalArgumentException(MESSAGE);
        }
        return string.matches(REGEX3);
    }

}
