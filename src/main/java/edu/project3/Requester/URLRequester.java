package edu.project3.Requester;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import static java.net.http.HttpClient.newHttpClient;

public class URLRequester implements LogRequester {
    private static final int SECONDS = 10;
    private static final Path PATH = Paths.get("src/main/java/edu/project3/Files/nginx_logs.txt");

    @Override
    public List<Path> requestFiles(String path) {
        try (var response = newHttpClient()) {
            var httpRequest = HttpRequest.newBuilder()
                .uri(new URI(path))
                .GET()
                .timeout(Duration.ofSeconds(SECONDS))
                .build();
            response.send(httpRequest, HttpResponse.BodyHandlers.ofFile(PATH));
            return List.of(PATH);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
