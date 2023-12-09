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
import static edu.project3.HttpUtils.STATUS;
import static edu.project3.StatsCounter.countAverageBodyByteSent;
import static edu.project3.StatsCounter.countResources;
import static edu.project3.StatsCounter.countStatus;

public final class MarkdownPrinter {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int BUILDER_CAPACITY = 10;
    private static final String TABLE_PATTERN = "|:-:|-:|\n";
    private static final String END_OF_LINE = "|\n";

    private MarkdownPrinter() {
    }

    public static void printMarkdown(Path path, List<Log> logList, LocalDate from, LocalDate to, String fileName) {
        Path newPath;
        if (Files.exists(path)) {
            newPath = Paths.get(path.getParent() + "/" + fileName + ".md");
        } else {
            LOGGER.info("File doesn't exist");
            return;
        }
        try (FileWriter fileWriter = new FileWriter(newPath.toFile())) {
            fileWriter.write(createMarkdown(path, logList, from, to));
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    private static String createMarkdown(Path path, List<Log> logList, LocalDate from, LocalDate to) {
        return createGeneralInfo(path, logList, from, to) + createResources(logList) + createStatus(logList);
    }

    private static String createGeneralInfo(Path path, List<Log> logList, LocalDate from, LocalDate to) {
        return String.format(
            "#### Общая информация\n\n|Метрика|Значение|\n%s|Файл(-ы)|%s%s|Начальная дата|%s%s"
                + "|Конечная дата|%s%s|Количество запросов|%d%s|Средний размер ответа|%d|\n\n",
            TABLE_PATTERN,
            path.getFileName(),
            END_OF_LINE,
            ((from == null) ? "-" : from),
            END_OF_LINE,
            ((to == null) ? "-" : to),
            END_OF_LINE,
            logList.size(),
            END_OF_LINE,
            countAverageBodyByteSent(logList)
        );
    }

    private static String createResources(List<Log> logList) {
        StringBuilder stringBuilder = new StringBuilder(BUILDER_CAPACITY);
        stringBuilder.append("#### Запрашиваемые ресурсы\n\n")
            .append("|Ресурс|Количество|\n")
            .append(TABLE_PATTERN);
        Map<String, Integer> mapOfResources = countResources(logList);
        for (var entry : mapOfResources.entrySet()) {
            stringBuilder.append("|").append(entry.getKey()).append("|")
                .append(entry.getValue()).append(END_OF_LINE);
        }
        stringBuilder.append("\n");
        return String.valueOf(stringBuilder);
    }

    private static String createStatus(List<Log> logList) {
        StringBuilder stringBuilder = new StringBuilder(BUILDER_CAPACITY);
        stringBuilder.append("#### Коды ответа\n\n")
            .append("|Код|Имя|Количество|\n")
            .append("|:-|:-:|-:|\n");
        Map<String, Integer> mapOfStatuses = countStatus(logList);
        for (var entry : mapOfStatuses.entrySet()) {
            stringBuilder.append("|").append(entry.getKey()).append("|")
                .append(STATUS.getOrDefault(Integer.valueOf(entry.getKey()), "-")).append("|")
                .append(entry.getValue()).append(END_OF_LINE);
        }
        return String.valueOf(stringBuilder);
    }
}
