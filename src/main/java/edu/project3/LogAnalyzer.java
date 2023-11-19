package edu.project3;

import edu.project3.Parsers.LogParser;
import edu.project3.Printers.AdocPrinter;
import edu.project3.Printers.MarkdownPrinter;
import edu.project3.Requester.LogRequester;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static edu.project3.Requester.LogRequester.getRequester;

public final class LogAnalyzer {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String PATH = "--path";
    private static final String FROM = "--from";
    private static final String TO = "--to";
    private static final String FORMAT = "--format";
    private final List<Path> filesPath;
    private final LogParser logParser;
    private String path;
    private LocalDate from;
    private LocalDate to;
    private String format;
    private final String fileName;
    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;

    public LogAnalyzer(String[] args, LogParser logParser, String fileName) {
        checkArguments(args);
        LogRequester logRequester = getRequester(path);
        this.logParser = logParser;
        filesPath = logRequester.requestFiles(path);
        this.fileName = fileName;
    }

    public void analyze() {
        List<Log> logs = logParser.parseData(filesPath, from, to);
        if (((format == null)) || (format.equals("markdown")) || (format.equals("md"))) {
            MarkdownPrinter.createMarkdown(Paths.get(path), logs, from, to, fileName);
        } else {
            AdocPrinter.createAdoc(Paths.get(path), logs, from, to, fileName);
        }
    }

    private LocalDate getLocalDate(String string) {
        try {
            return LocalDate.parse(string, formatter);
        } catch (DateTimeParseException dateTimeParseException) {
            LOGGER.info("Failed to parse Date");
            return null;
        }
    }

    @SuppressWarnings("InnerAssignment")
    private void checkArguments(String[] args) {
        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case PATH -> path = args[i + 1];
                case FROM -> from = getLocalDate(args[i + 1]);
                case TO -> to = getLocalDate(args[i + 1]);
                case FORMAT -> format = args[i + 1];
                default -> {
                }
            }
        }
    }
}
