package edu.hw10.Task1.Generators;

import edu.hw10.Task1.Annotations.Max;
import edu.hw10.Task1.Annotations.Min;
import java.lang.annotation.Annotation;
import java.util.Random;

public class FloatGenerator implements Generator<Float> {
    private final Random random = new Random();

    @Override
    public Float generate(Annotation[] annotations) {
        float min = Float.MIN_VALUE;
        float max = Float.MAX_VALUE;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min) {
                min = (float) (((Min) annotation).value());
            }
            if (annotation instanceof Max) {
                max = (float) (((Max) annotation).value());
            }
        }
        return random.nextFloat(min, max);
    }
}
