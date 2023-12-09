package edu.hw6.Task5;

import java.util.Arrays;
import static edu.hw6.Task5.HackerNews.hackerNewsTopStories;
import static edu.hw6.Task5.HackerNews.news;

public final class Main {
    private static final long ID = 37570037;

    private Main() {
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public static void main(String[] args) {
        System.out.println(Arrays.toString(hackerNewsTopStories()));
        System.out.println(news(ID));
    }
}
