package edu.hw6.Task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public class DiskMap implements Map<String, String> {
    private final Path path;
    private final Map<String, String> map = new HashMap<>();
    private final List<File> list = new ArrayList<>();
    private final String txt = ".txt";

    public DiskMap(String string) {
        path = Path.of("src\\main\\java\\edu\\hw6\\Task1\\Files\\" + string);
        File file = path.toFile();
        file.delete();
        file.mkdirs();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public String put(String key, String value) {
        if ((key == null) || (value == null)) {
            return key;
        }
        String previousValue = map.get(key);
        map.put(key, value);
        Path keyPath = Path.of(path + "/" + key + txt);
        File file = keyPath.toFile();
        if (!list.contains(file)) {
            list.add(keyPath.toFile());
        }
        if (file.exists()) {
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(value);
        } catch (IOException ioE) {
            throw new RuntimeException();
        }
        return previousValue;
    }

    @Override
    public String get(Object key) {
        if (key == null) {
            return null;
        }
        if (map.containsKey(key.toString())) {
            for (File file : list) {
                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                    if (file.getName().equals(key + txt)) {
                        String string = bufferedReader.readLine();
                        return Objects.requireNonNullElse(string, "");
                    }
                } catch (Exception e) {
                    throw new RuntimeException();
                }
            }
        }
        return null;
    }

    @Override
    public String remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return map.values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return map.entrySet();
    }
}
