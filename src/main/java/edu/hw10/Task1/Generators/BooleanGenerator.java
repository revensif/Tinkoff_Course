package edu.hw10.Task1.Generators;

import java.lang.annotation.Annotation;
import java.util.Random;

public class BooleanGenerator implements Generator<Boolean> {
    private final Random random = new Random();

    @Override
    public Boolean generate(Annotation[] annotations) {
        return random.nextBoolean();
    }
}
