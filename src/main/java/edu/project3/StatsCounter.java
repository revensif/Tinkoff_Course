package edu.project3;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class StatsCounter {

    private StatsCounter() {
    }

    public static long countAverageBodyByteSent(List<Log> logList) {
        long averageBodyByteSent = 0;
        for (Log log : logList) {
            averageBodyByteSent += log.bodyBytesSent();
        }
        return averageBodyByteSent / logList.size();
    }

    public static Map<String, Integer> countResources(List<Log> logList) {
        HashMap<String, Integer> mapOfResources = new HashMap<>();
        logList.forEach((log) -> mapOfResources.merge(log.resource(), 1, Integer::sum));
        return mapOfResources
            .entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));
    }

    public static Map<String, Integer> countStatus(List<Log> logList) {
        HashMap<String, Integer> mapOfStatuses = new HashMap<>();
        logList.forEach((log) -> mapOfStatuses.merge(log.status(), 1, Integer::sum));
        return mapOfStatuses
            .entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));
    }
}
