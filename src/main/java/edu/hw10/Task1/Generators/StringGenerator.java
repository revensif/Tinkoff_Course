package edu.hw10.Task1.Generators;

import java.lang.annotation.Annotation;
import java.util.Random;

public class StringGenerator implements Generator<String> {
    private static final int MAX_STRING_LENGTH = 12;
    private static final int BUILDER_CAPACITY = 10;
    private final Random random = new Random();
    private final char[] alphabet =
        {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z'};

    @Override
    public String generate(Annotation[] annotations) {
        StringBuilder stringBuilder = new StringBuilder(BUILDER_CAPACITY);
        int length = random.nextInt(2, MAX_STRING_LENGTH);
        for (int i = 0; i < length; i++) {
            int character = random.nextInt(alphabet.length);
            if (random.nextBoolean()) {
                stringBuilder.append(alphabet[character]);
            } else {
                stringBuilder.append(Character.toUpperCase(alphabet[character]));
            }
        }
        return String.valueOf(stringBuilder);
    }
}
