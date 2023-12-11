package edu.hw10.Task1.Generators;

import edu.hw10.Task1.Annotations.Max;
import edu.hw10.Task1.Annotations.Min;
import java.lang.annotation.Annotation;
import java.util.Random;

public class ByteGenerator implements Generator<Byte> {
    private final Random random = new Random();

    @Override
    public Byte generate(Annotation[] annotations) {
        byte min = Byte.MIN_VALUE;
        byte max = Byte.MAX_VALUE;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min) {
                min = (byte) (((Min) annotation).value());
            }
            if (annotation instanceof Max) {
                max = (byte) (((Max) annotation).value());
            }
        }
        return (byte) random.nextInt(min, max);
    }
}
