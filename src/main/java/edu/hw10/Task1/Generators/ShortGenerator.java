package edu.hw10.Task1.Generators;

import edu.hw10.Task1.Annotations.Max;
import edu.hw10.Task1.Annotations.Min;
import java.lang.annotation.Annotation;
import java.util.Random;

public class ShortGenerator implements Generator<Short> {
    private final Random random = new Random();

    @Override
    public Short generate(Annotation[] annotations) {
        short min = Short.MIN_VALUE;
        short max = Short.MAX_VALUE;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min) {
                min = (short) (((Min) annotation).value());
            }
            if (annotation instanceof Max) {
                max = (short) (((Max) annotation).value());
            }
        }
        return (short) random.nextInt(min, max);
    }
}
