package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw6.Task5.HackerNews.hackerNewsTopStories;
import static edu.hw6.Task5.HackerNews.news;
import static org.assertj.core.api.Assertions.assertThat;

public class Task5Test {

    @Test
    @DisplayName("news(long id) Test")
    void shouldReturnTitleOfNews() {
        assertThat(news(37570037)).isEqualTo("JDK 21 Release Notes");
        assertThat(news(Long.MAX_VALUE)).isEmpty();
        assertThat(news(Long.MIN_VALUE)).isEmpty();
        assertThat(news(38274568)).isEqualTo("Orthodox Privilege (2020)");
    }

    @Test
    @DisplayName("hackerNewsTopStories() Test")
    void shouldReturnHackerNewsTopStories() {
        assertThat(hackerNewsTopStories()).isNotEmpty();
    }
}
