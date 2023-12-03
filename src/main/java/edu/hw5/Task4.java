package edu.hw5;

public final class Task4 {
    private static final String SPECIAL_SYMBOLS = ".*[~!@#$%^&*|].*";

    private Task4() {
    }

    public static boolean isPasswordHasSpecialSymbol(String password) {
        return password.matches(SPECIAL_SYMBOLS);
    }
}
