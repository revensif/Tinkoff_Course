package edu.hw3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Task3 {
    private Task3() {
    }

    public static <T> Map<T, Integer> freqDict(List<T> list) {
        if ((list == null) || (list.isEmpty())) {
            throw new IllegalArgumentException("The list should not be empty");
        }
        Map<T, Integer> map = new HashMap<>();
        list.forEach((element) -> map.merge(element, 1, Integer::sum));
        return map;
    }
}
