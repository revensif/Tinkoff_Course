package edu.project3.Parsers;

import edu.project3.Log;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogParser {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int HOURS = 23;
    private static final int MINUTES = 59;
    private static final int SECONDS = 59;
    private static final int REQUEST_SIZE = 3;
    private static final int TIME_LOCAL = 3;
    private static final int REQUEST = 4;
    private static final int STATUS = 5;
    private static final int BODY_BYTE_SENT = 6;
    private static final Pattern PATTERN_FOR_LOG =
        Pattern.compile("^(.*) - (.*) \\[(.*)] \"(.*)\" (\\d{3}) (\\d+) \"(.*)\" \"(.*)\"$");
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter
        .ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.UK);

    public List<Log> parseData(List<Path> logFiles, LocalDate from, LocalDate to) {
        List<Log> logList = new ArrayList<>();
        for (Path file : logFiles) {
            List<String> linesInFile = readFile(file);
            for (String line : linesInFile) {
                if (line.matches(String.valueOf(PATTERN_FOR_LOG))) {
                    Log log = getLog(line);
                    if (isCorrectDate(log.timeLocal(), from, to)) {
                        logList.add(log);
                    }
                }
            }
        }
        return logList;
    }

    private static boolean isCorrectDate(OffsetDateTime dateTime, LocalDate from, LocalDate to) {
        if ((from == null) && (to == null)) {
            return true;
        } else if (from == null) {
            LocalDateTime localTo = LocalDateTime.of(to, LocalTime.of(HOURS, MINUTES, SECONDS));
            return (dateTime.isBefore(localTo.atOffset(ZoneOffset.UTC)));
        } else if (to == null) {
            LocalDateTime localFrom = LocalDateTime.of(from, LocalTime.of(0, 0, 0));
            return (dateTime.isAfter(localFrom.atOffset(ZoneOffset.UTC)));
        }
        LocalDateTime localFrom = LocalDateTime.of(from, LocalTime.of(0, 0, 0));
        LocalDateTime localTo = LocalDateTime.of(to, LocalTime.of(HOURS, MINUTES, SECONDS));
        return ((dateTime.isAfter(localFrom.atOffset(ZoneOffset.UTC)))
            && (dateTime.isBefore(localTo.atOffset(ZoneOffset.UTC))));
    }

    private static List<String> readFile(Path path) {
        try {
            return Files.readAllLines(path);
        } catch (IOException ioException) {
            LOGGER.info("File is empty");
            return List.of();
        }
    }

    private static Log getLog(String line) {
        Matcher matcher = PATTERN_FOR_LOG.matcher(line);
        matcher.find();
        OffsetDateTime offsetDateTime = getLocalTime(matcher.group(TIME_LOCAL));
        String resource = getResource(matcher.group(REQUEST));
        String status = matcher.group(STATUS);
        int bodyBytesSent = Integer.parseInt(matcher.group(BODY_BYTE_SENT));
        return new Log(offsetDateTime, resource, status, bodyBytesSent);
    }

    private static OffsetDateTime getLocalTime(String line) {
        try {
            return OffsetDateTime.parse(line, FORMATTER);
        } catch (DateTimeParseException dateTimeParseException) {
            LOGGER.info("Failed to parse Date");
            return null;
        }
    }

    private static String getResource(String line) {
        String[] lines = line.split(" ");
        if (lines.length != REQUEST_SIZE) {
            return null;
        } else {
            return lines[1];
        }
    }
}
