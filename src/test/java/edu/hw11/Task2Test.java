package edu.hw11;

import edu.hw11.Task2.ArithmeticUtils;
import edu.hw11.Task2.ArithmeticUtilsMultiplication;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    private static final int FIRST_VALUE = 5;
    private static final int SECOND_VALUE = 6;

    @Test
    @DisplayName("ByteBuddy Class Creation")
    void shouldCreateClassAndReturnHelloByteBuddy() {
        Class<? extends ArithmeticUtils> byteBuddy = new ByteBuddy()
            .subclass(ArithmeticUtils.class)
            .method(named("sum")).intercept(MethodDelegation.to(ArithmeticUtilsMultiplication.class))
            .make()
            .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
            .getLoaded();
        int expected = ArithmeticUtilsMultiplication.multiply(FIRST_VALUE, SECOND_VALUE);
        try {
            int actual = byteBuddy.getDeclaredConstructor().newInstance().sum(FIRST_VALUE, SECOND_VALUE);
            assertThat(actual).isEqualTo(expected);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
