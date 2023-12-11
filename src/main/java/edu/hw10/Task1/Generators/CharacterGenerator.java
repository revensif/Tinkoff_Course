package edu.hw10.Task1.Generators;

import edu.hw10.Task1.Annotations.Max;
import edu.hw10.Task1.Annotations.Min;
import java.lang.annotation.Annotation;
import java.util.Random;

public class CharacterGenerator implements Generator<Character> {
    private final Random random = new Random();

    @Override
    public Character generate(Annotation[] annotations) {
        char min = Character.MIN_VALUE;
        char max = Character.MAX_VALUE;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min) {
                min = (char) (((Min) annotation).value());
            }
            if (annotation instanceof Max) {
                max = (char) (((Max) annotation).value());
            }
        }
        return (char) random.nextInt(min, max);
    }
}
