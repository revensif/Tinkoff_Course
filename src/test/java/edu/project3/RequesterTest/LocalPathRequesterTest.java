package edu.project3.RequesterTest;

import edu.project3.Requester.LocalPathRequester;
import edu.project3.Requester.LogRequester;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LocalPathRequesterTest {
    private static final Path path = Paths.get("src/main/java/edu/project3/Files");

    @Test
    @DisplayName("requestFiles() Test : Correct Input")
    void shouldRequestFilesAndReturnPath() {
        LogRequester logRequester = new LocalPathRequester();
        List<Path> files = logRequester.requestFiles(path + "/nginx_logs.txt");
        assertThat(files).isNotEmpty();
        assertThat(files.size()).isEqualTo(1);
        assertThat(files.get(0)).isRegularFile();
    }

    @Test
    @DisplayName("requestFiles() Test : Incorrect Input")
    void shouldRequestFilesAndReturnNothing() {
        LogRequester logRequester = new LocalPathRequester();
        List<Path> files = logRequester.requestFiles(path + "/nginx.txt");
        assertThat(files).isEmpty();
        assertThrows(IndexOutOfBoundsException.class, () -> files.get(0));
    }
}
