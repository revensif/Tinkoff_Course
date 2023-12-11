package edu.hw10.Task1;

import edu.hw10.Task1.Generators.BooleanGenerator;
import edu.hw10.Task1.Generators.ByteGenerator;
import edu.hw10.Task1.Generators.CharacterGenerator;
import edu.hw10.Task1.Generators.DoubleGenerator;
import edu.hw10.Task1.Generators.FloatGenerator;
import edu.hw10.Task1.Generators.Generator;
import edu.hw10.Task1.Generators.IntegerGenerator;
import edu.hw10.Task1.Generators.LongGenerator;
import edu.hw10.Task1.Generators.ShortGenerator;
import edu.hw10.Task1.Generators.StringGenerator;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;
import static java.util.Map.entry;

public class RandomObjectGenerator {
    private static final Map<Class<?>, Generator<?>> GENERATORS = Map.ofEntries(
        entry(boolean.class, new BooleanGenerator()),
        entry(Boolean.class, new BooleanGenerator()),
        entry(byte.class, new ByteGenerator()),
        entry(Byte.class, new ByteGenerator()),
        entry(char.class, new CharacterGenerator()),
        entry(Character.class, new CharacterGenerator()),
        entry(double.class, new DoubleGenerator()),
        entry(Double.class, new DoubleGenerator()),
        entry(float.class, new FloatGenerator()),
        entry(Float.class, new FloatGenerator()),
        entry(int.class, new IntegerGenerator()),
        entry(Integer.class, new IntegerGenerator()),
        entry(long.class, new LongGenerator()),
        entry(Long.class, new LongGenerator()),
        entry(short.class, new ShortGenerator()),
        entry(Short.class, new ShortGenerator()),
        entry(String.class, new StringGenerator())
    );

    public <T> T nextObject(Class<T> classToCreate) {
        Constructor<?> constructor = getConstructorWithLargestNumberOfParameters(classToCreate);
        constructor.setAccessible(true);
        Parameter[] parameters = constructor.getParameters();
        Object[] values = getRandomParameters(parameters);
        try {
            return classToCreate.cast(constructor.newInstance(values));
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T nextObject(Class<T> classToCreate, String creationMethod) {
        Method method = getCreationMethod(classToCreate, creationMethod);
        method.setAccessible(true);
        Parameter[] parameters = method.getParameters();
        Object[] values = getRandomParameters(parameters);
        try {
            return classToCreate.cast(method.invoke(null, values));
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private Constructor<?> getConstructorWithLargestNumberOfParameters(Class<?> classToCreate) {
        Constructor<?>[] constructors = classToCreate.getDeclaredConstructors();
        if (constructors.length < 1) {
            throw new IllegalArgumentException();
        }
        Constructor<?> result = constructors[0];
        for (Constructor<?> constructor : constructors) {
            if (result.getParameterCount() < constructor.getParameterCount()) {
                result = constructor;
            }
        }
        return result;
    }

    private Object[] getRandomParameters(Parameter[] parameters) {
        Object[] values = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            Class<?> type = parameters[i].getType();
            Generator<?> generator = GENERATORS.get(type);
            values[i] = generator.generate(parameters[i].getAnnotations());
        }
        return values;
    }

    private Method getCreationMethod(Class<?> classToCreate, String creationMethod) {
        Method[] methods = classToCreate.getDeclaredMethods();
        Method result = null;
        for (Method method : methods) {
            if (method.getName().equals(creationMethod)) {
                result = method;
            }
        }
        if (result == null) {
            throw new IllegalArgumentException();
        }
        return result;
    }
}
