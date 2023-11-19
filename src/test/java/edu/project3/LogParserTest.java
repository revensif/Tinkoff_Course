package edu.project3;

import edu.project3.Parsers.LogParser;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LogParserTest {
    private static final Path path = Paths.get("src/main/java/edu/project3/Files/nginx_logs.txt");

    @Test
    @DisplayName("parseData() Test : Correct Input")
    void shouldParseDataAndReturnLogs() {
        LogParser logParser = new LogParser();
        LocalDate date = LocalDate.of(2015, 5, 17);
        List<Log> logList = logParser.parseData(List.of(path), date, date);
        assertThat(logList).isNotEmpty();
        assertThat(logList.size()).isEqualTo(1966);
        logList = logParser.parseData(List.of(path), date, date.plusYears(1));
        assertThat(logList).isNotEmpty();
        assertThat(logList.size()).isEqualTo(51462);
    }

    @Test
    @DisplayName("parseData() Test : Incorrect Input")
    void shouldParseDataAndReturnNothing() {
        LogParser logParser = new LogParser();
        LocalDate date = LocalDate.of(2015, 5, 17);
        List<Log> logList = logParser.parseData(List.of(path), date, date.minusDays(1));
        assertThat(logList).isEmpty();
    }
}
