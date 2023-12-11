package edu.hw10.Task1.Generators;

import edu.hw10.Task1.Annotations.Max;
import edu.hw10.Task1.Annotations.Min;
import java.lang.annotation.Annotation;
import java.util.Random;

public class DoubleGenerator implements Generator<Double> {
    private final Random random = new Random();

    @Override
    public Double generate(Annotation[] annotations) {
        double min = Double.MIN_VALUE;
        double max = Double.MAX_VALUE;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min) {
                min = ((Min) annotation).value();
            }
            if (annotation instanceof Max) {
                max = ((Max) annotation).value();
            }
        }
        return random.nextDouble(min, max);
    }
}
