package edu.hw11;

import edu.hw11.Task3.FibonacciMethod;
import java.lang.reflect.InvocationTargetException;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.jar.asm.Opcodes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {

    @Test
    @DisplayName("FibonacciMethod Test")
    public void shouldCalculateFib()
        throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<?> dynamicClass = new ByteBuddy().subclass(Object.class)
            .name("Fibonacci")
            .defineMethod("fib", long.class, Opcodes.ACC_PUBLIC)
            .withParameter(int.class)
            .intercept(new Implementation.Simple(new FibonacciMethod()))
            .make()
            .load(getClass().getClassLoader())
            .getLoaded();
        Object instance = dynamicClass.getDeclaredConstructor().newInstance();
        long[] expected = {0L, 0L, 0L, 1L, 1L, 2L, 3L, 5L, 8L, 13L, 21L};
        for (int i = 0; i < expected.length; i++) {
            assertThat(dynamicClass.getMethod("fib", int.class).invoke(instance, i - 2)).isEqualTo(expected[i]);
        }
    }
}
