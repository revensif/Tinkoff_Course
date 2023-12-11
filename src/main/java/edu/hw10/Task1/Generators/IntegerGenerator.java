package edu.hw10.Task1.Generators;

import edu.hw10.Task1.Annotations.Max;
import edu.hw10.Task1.Annotations.Min;
import java.lang.annotation.Annotation;
import java.util.Random;

public class IntegerGenerator implements Generator<Integer> {
    private final Random random = new Random();

    @Override
    public Integer generate(Annotation[] annotations) {
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min) {
                min = (int) (((Min) annotation).value());
            }
            if (annotation instanceof Max) {
                max = (int) (((Max) annotation).value());
            }
        }
        return random.nextInt(min, max);
    }
}
