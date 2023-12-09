package edu.project3.RequesterTest;

import edu.project3.Requester.LogRequester;
import edu.project3.Requester.URLRequester;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class URLRequesterTest {
    private static final Path filePath = Paths.get("src/main/java/edu/project3/Files/nginx_logs.txt");
    private static final String path =
        "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs";

    @Test
    @DisplayName("requestFiles() Test : Correct Input")
    void shouldRequestFilesAndReturnPath() {
        LogRequester logRequester = new URLRequester();
        List<Path> files = logRequester.requestFiles(path + "/nginx_logs");
        assertThat(files).isNotEmpty();
        assertThat(files.size()).isEqualTo(1);
        assertThat(files.get(0)).isRegularFile();
    }

    @Test
    @DisplayName("requestFiles() Test : Incorrect Input")
    void shouldRequestFilesAndReturnNothing() {
        LogRequester logRequester = new URLRequester();
        List<Path> files = logRequester.requestFiles(path + "/asdkjasdjasi");
        assertThat(files).isNotEmpty();
        assertThat(files.size()).isEqualTo(1);
        assertThat(files.get(0)).isEqualTo(filePath);
    }
}
