package edu.hw10.Task2;

import java.io.FileWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CacheProxy {
    private static final Map<String, Object> CACHE = new HashMap<>();

    private CacheProxy() {
    }

    public static <T> T create(T object, Class<? extends T> classCreate, Path fileForSave) {
        ClassLoader classLoader = classCreate.getClassLoader();
        Class<?>[] interfaces = classCreate.getInterfaces();
        InvocationHandler handler = ((Object proxy, Method method, Object[] args) -> {
            Object result = method.invoke(object, args);
            if (method.isAnnotationPresent(Cache.class)) {
                String key = method.getName() + Arrays.toString(args);
                if (CACHE.containsKey(key)) {
                    return CACHE.get(key);
                }
                CACHE.put(key, result);
                if (method.getAnnotation(Cache.class).persist()) {
                    if (!Files.exists(fileForSave)) {
                        Files.createFile(fileForSave);
                    }
                    try (FileWriter fileWriter = new FileWriter(fileForSave.toFile(), true)) {
                        fileWriter.write(key + " = " + result + "\n");
                    }
                }
            }
            return result;
        });
        return (T) Proxy.newProxyInstance(classLoader, interfaces, handler);
    }
}
