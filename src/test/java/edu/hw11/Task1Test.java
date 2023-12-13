package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {
    private static final String BYTE_BUDDY = "Hello, ByteBuddy!";

    @Test
    @DisplayName("ByteBuddy Class Creation")
    void shouldCreateClassAndReturnHelloByteBuddy() {
        Class<?> byteBuddy = new ByteBuddy()
            .subclass(Object.class)
            .method(named("toString")).intercept(FixedValue.value(BYTE_BUDDY))
            .make()
            .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
            .getLoaded();
        try {
            assertThat(byteBuddy.getDeclaredConstructor().newInstance().toString()).isEqualTo(BYTE_BUDDY);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
