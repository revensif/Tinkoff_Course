package edu.project3.PrintersTest;

import edu.project3.Log;
import edu.project3.Parsers.LogParser;
import edu.project3.Printers.AdocPrinter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AdocPrinterTest {
    private static final Path path = Paths.get("src/main/java/edu/project3/Files");

    @Test
    @DisplayName("createAdoc() Test : Correct Input")
    void shouldCreateAdoc() throws IOException {
        LocalDate date = LocalDate.of(2015, 5, 17);
        LogParser logParser = new LogParser();
        Path logPath = Paths.get(path + "/nginx_logs.txt");
        Path filePath = Paths.get(path + "/test2.adoc");
        List<Log> log = logParser.parseData(List.of(logPath), date, date);
        if (Files.exists(filePath)) {
            Files.delete(filePath);
        }
        AdocPrinter.createAdoc(logPath, log, date, date, "test2");
        assertThat(Files.exists(filePath)).isTrue();
        assertThat(Files.isRegularFile(filePath)).isTrue();
    }

    @Test
    @DisplayName("createAdoc() Test : Correct Input")
    void shouldCreateAdocAndDoNothing() throws IOException {
        LocalDate date = LocalDate.of(2015, 5, 17);
        LogParser logParser = new LogParser();
        Path logPath = Paths.get(path + "/nginx.txt");
        Path filePath = Paths.get(path + "/test2.adoc");
        List<Log> log = logParser.parseData(List.of(logPath), date, date);
        if (Files.exists(filePath)) {
            Files.delete(filePath);
        }
        AdocPrinter.createAdoc(logPath, log, date, date, "test2");
        assertThat(Files.exists(filePath)).isFalse();
    }
}
