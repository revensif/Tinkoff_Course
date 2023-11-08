package edu.hw3;

import java.util.HashMap;
import java.util.Map;

public final class Task1 {
    private static final Map<Character, Character> MAP = new HashMap<>();

    static {
        for (char c = 'A'; c <= 'Z'; c++) {
            MAP.put(c, (char) ('Z' - c + 'A'));
        }
        for (char c = 'a'; c <= 'z'; c++) {
            MAP.put(c, (char) ('z' - c + 'a'));
        }
    }

    private Task1() {
    }

    public static String atbash(String text) {
        if ((text == null) || (text.trim().isEmpty())) {
            return "";
        }
        char[] cipheredText = text.toCharArray();
        for (int i = 0; i < cipheredText.length; i++) {
            if (MAP.containsKey(text.charAt(i))) {
                cipheredText[i] = MAP.get(text.charAt(i));
            }
        }
        return new String(cipheredText);
    }
}
