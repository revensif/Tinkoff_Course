package edu.project3.PrintersTest;

import edu.project3.Log;
import edu.project3.Parsers.LogParser;
import edu.project3.Printers.MarkdownPrinter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MarkdownPrinterTest {
    private static final Path path = Paths.get("src/main/java/edu/project3/Files");

    @Test
    @DisplayName("createMarkdown() Test : Correct Input")
    void shouldCreateAdoc() throws IOException {
        LocalDate date = LocalDate.of(2015, 5, 17);
        LogParser logParser = new LogParser();
        Path logPath = Paths.get(path + "/nginx_logs.txt");
        Path filePath = Paths.get(path + "/test2.md");
        List<Log> log = logParser.parseData(List.of(logPath), date, date);
        if (Files.exists(filePath)) {
            Files.delete(filePath);
        }
        MarkdownPrinter.printMarkdown(logPath, log, date, date, "test2");
        assertThat(Files.exists(filePath)).isTrue();
        assertThat(Files.isRegularFile(filePath)).isTrue();
    }

    @Test
    @DisplayName("createAdoc() Test : Correct Input")
    void shouldCreateMarkdownAndDoNothing() throws IOException {
        LocalDate date = LocalDate.of(2015, 5, 17);
        LogParser logParser = new LogParser();
        Path logPath = Paths.get(path + "/nginx.txt");
        Path filePath = Paths.get(path + "/test2.md");
        List<Log> log = logParser.parseData(List.of(logPath), date, date);
        if (Files.exists(filePath)) {
            Files.delete(filePath);
        }
        MarkdownPrinter.printMarkdown(logPath, log, date, date, "test2");
        assertThat(Files.exists(filePath)).isFalse();
    }
}
