package edu.project3.Printers;

import edu.project3.Log;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static edu.project3.StatsCounter.countAverageBodyByteSent;
import static edu.project3.StatsCounter.countRequests;
import static edu.project3.StatsCounter.countResources;
import static edu.project3.StatsCounter.countStatus;
import static java.util.Map.entry;

public final class AdocPrinter {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int BUILDER_CAPACITY = 10;
    private static final String ADOC_PATTERN = "====\n";
    private static final String END_OF_LINE = "|\n";
    private static final Map<Integer, String> STATUS = Map.ofEntries(
        entry(200, "OK"),
        entry(301, "Moved Permanently"),
        entry(302, "Found"),
        entry(304, "Not Modified"),
        entry(403, "Forbidden"),
        entry(404, "Not Found"),
        entry(500, "Internal Server Error"),
        entry(502, "Bad Gateway"),
        entry(503, "Service Unavailable"),
        entry(504, "Gateway Timeout")
    );

    private AdocPrinter() {
    }

    public static void createAdoc(Path path, List<Log> logList, LocalDate from, LocalDate to, String fileName) {
        Path newPath;
        if (Files.exists(path)) {
            newPath = Paths.get(path.getParent() + "/" + fileName + ".adoc");
        } else {
            LOGGER.info("File doesn't exist");
            return;
        }
        try (FileWriter fileWriter = new FileWriter(newPath.toFile())) {
            fileWriter.write(printMarkdown(path, logList, from, to));
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    private static String printMarkdown(Path path, List<Log> logList, LocalDate from, LocalDate to) {
        return printGeneralInfo(path, logList, from, to) + printResources(logList) + printStatus(logList);
    }

    private static String printGeneralInfo(Path path, List<Log> logList, LocalDate from, LocalDate to) {
        return "|Общая информация\n"
            + ADOC_PATTERN
            + "|Метрика|Значение\n"
            + "|Файл(-ы)| " + path.getFileName() + END_OF_LINE
            + "|Начальная дата|" + ((from == null) ? "-" : from) + END_OF_LINE
            + "|Конечная дата|" + ((to == null) ? "-" : to) + END_OF_LINE
            + "|Количество запросов|" + countRequests(logList) + END_OF_LINE
            + "|Средний размер ответа|" + countAverageBodyByteSent(logList) + "|\n\n";
    }

    private static String printResources(List<Log> logList) {
        StringBuilder stringBuilder = new StringBuilder(BUILDER_CAPACITY);
        stringBuilder.append("|Запрашиваемые ресурсы\n")
            .append(ADOC_PATTERN)
            .append("|Ресурс|Количество|\n");
        Map<String, Integer> mapOfResources = countResources(logList);
        for (var entry : mapOfResources.entrySet()) {
            stringBuilder.append("|").append(entry.getKey()).append("|")
                .append(entry.getValue()).append(END_OF_LINE);
        }
        stringBuilder.append("\n");
        return String.valueOf(stringBuilder);
    }

    private static String printStatus(List<Log> logList) {
        StringBuilder stringBuilder = new StringBuilder(BUILDER_CAPACITY);
        stringBuilder.append("|Коды ответа\n")
            .append(ADOC_PATTERN)
            .append("|Код|Имя|Количество|\n");
        Map<String, Integer> mapOfStatuses = countStatus(logList);
        for (var entry : mapOfStatuses.entrySet()) {
            stringBuilder.append("|").append(entry.getKey()).append("|")
                .append(STATUS.getOrDefault(Integer.valueOf(entry.getKey()), "-")).append("|")
                .append(entry.getValue()).append(END_OF_LINE);
        }
        return String.valueOf(stringBuilder);
    }
}
