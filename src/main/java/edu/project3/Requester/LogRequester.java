package edu.project3.Requester;

import java.nio.file.Path;
import java.util.List;

public interface LogRequester {
    String HTTP_S_PATTERN = "^https?://.*$";

    List<Path> requestFiles(String path);

    static LogRequester getRequester(String path) {
        if (path.matches(HTTP_S_PATTERN)) {
            return new URLRequester();
        } else {
            return new LocalPathRequester();
        }
    }
}
