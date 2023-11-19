package edu.project3;

import edu.project3.Parsers.LogParser;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LogAnalyzerTest {
    private static final Path path = Paths.get("src/main/java/edu/project3/Files");

    @Test
    @DisplayName("analyze() Test : Correct Input")
    void shouldAnalyzeLogsAndCreateFile() throws IOException {
        LogAnalyzer logAnalyzer =
            new LogAnalyzer(
                new String[] {"--path", path + "/nginx_logs.txt"},
                new LogParser(),
                "test"
            );
        Path filePath = Paths.get(path + "/" + "test.md");
        if (Files.exists(filePath)) {
            Files.delete(filePath);
        }
        logAnalyzer.analyze();
        assertThat(Files.exists(filePath)).isTrue();
    }

    @Test
    @DisplayName("analyze() Test : Incorrect Input")
    void shouldAnalyzeLogsAndCreateNothing() throws IOException {
        LogAnalyzer logAnalyzer =
            new LogAnalyzer(
                new String[] {"--path", path + "/nginx.txt"},
                new LogParser(),
                "test"
            );
        Path filePath = Paths.get(path + "/" + "test.md");
        if (Files.exists(filePath)) {
            Files.delete(filePath);
        }
        logAnalyzer.analyze();
        assertThat(Files.exists(filePath)).isFalse();
    }
}
