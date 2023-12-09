package edu.project3;

import edu.project3.Parsers.LogParser;

public final class Main {

    private Main() {
    }

    @SuppressWarnings("MultipleStringLiterals")
    public static void main(String[] args) {
        LogAnalyzer logAnalyzer =
            new LogAnalyzer(new String[] {"--path", "src/main/java/edu/project3/files/nginx_logs.txt", "--from",
                "2015-05-17", "--to", "2015-05-17"}, new LogParser(), "logReport");
        logAnalyzer.analyze();
        logAnalyzer =
            new LogAnalyzer(new String[] {"--path", "src/main/java/edu/project3/files/nginx_logs.txt", "--from",
                "2015-06-02", "--format",
                "adoc"}, new LogParser(), "logReport");
        logAnalyzer.analyze();
    }
}
