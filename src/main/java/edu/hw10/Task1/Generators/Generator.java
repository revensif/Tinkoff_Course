package edu.hw10.Task1.Generators;

import java.lang.annotation.Annotation;

public interface Generator<V> {

    V generate(Annotation[] annotations);
}
