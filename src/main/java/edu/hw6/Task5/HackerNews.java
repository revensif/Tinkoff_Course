package edu.hw6.Task5;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.net.http.HttpClient.newHttpClient;

public final class HackerNews {
    private static final Pattern TITLE_PATTERN = Pattern.compile("^.*(\"title\"):\"([^\"]*)\".*$");
    private static final int SECONDS = 10;

    private HackerNews() {
    }

    public static long[] hackerNewsTopStories() {
        try (var response = newHttpClient()) {
            var httpRequest = HttpRequest.newBuilder()
                .uri(new URI("https://hacker-news.firebaseio.com/v0/topstories.json"))
                .GET()
                .timeout(Duration.ofSeconds(SECONDS))
                .build();
            var getResponse = response.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String data = getResponse.body();
            data = data.replace("[", "");
            data = data.replace("]", "");
            String[] values = data.split(",");
            return Arrays.stream(values)
                .mapToLong(Long::parseLong)
                .toArray();
        } catch (Exception exception) {
            return new long[] {};
        }
    }

    public static String news(long id) {
        try (var response = newHttpClient()) {
            var httpRequest = HttpRequest.newBuilder()
                .uri(new URI("https://hacker-news.firebaseio.com/v0/item/" + id + ".json"))
                .GET()
                .timeout(Duration.ofSeconds(SECONDS))
                .build();
            var getResponse = response.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String data = getResponse.body();
            Matcher matcher = TITLE_PATTERN.matcher(data);
            if (matcher.find()) {
                data = matcher.group(2);
            } else {
                data = "";
            }
            return data;
        } catch (Exception ignored) {
            return "";
        }
    }
}
