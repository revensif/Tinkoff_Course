package edu.hw10.Task1.Generators;

import edu.hw10.Task1.Annotations.Max;
import edu.hw10.Task1.Annotations.Min;
import java.lang.annotation.Annotation;
import java.util.Random;

public class LongGenerator implements Generator<Long> {
    private final Random random = new Random();

    @Override
    public Long generate(Annotation[] annotations) {
        long min = Long.MIN_VALUE;
        long max = Long.MAX_VALUE;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min) {
                min = (long) (((Min) annotation).value());
            }
            if (annotation instanceof Max) {
                max = (long) (((Max) annotation).value());
            }
        }
        return random.nextLong(min, max);
    }
}
