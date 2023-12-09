package edu.hw6;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw6.Task3.AbstractFilter.READABLE;
import static edu.hw6.Task3.AbstractFilter.REGULAR_FILE;
import static edu.hw6.Task3.AbstractFilter.WRITABLE;
import static edu.hw6.Task3.GlobAbstractFilter.globMatches;
import static edu.hw6.Task3.MagicNumberAbstractFilter.magicNumber;
import static edu.hw6.Task3.RegexAbstractFilter.regexContains;
import static edu.hw6.Task3.WeightAbstractFilter.largerThan;
import static edu.hw6.Task3.WeightAbstractFilter.lessThan;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {
    private static final Path PATH = Paths.get("src/main/java/edu/hw6/Task3/Files");

    @Test
    @DisplayName("Task3Test : .png Test")
    void shouldFilterFilesAndFindPng() throws IOException {
        DirectoryStream.Filter<Path> filter = REGULAR_FILE
            .and(READABLE)
            .and(largerThan(1))
            .and(lessThan(10000))
            .and(magicNumber((byte) 0x89, (byte) 'P', (byte) 'N', (byte) 'G'))
            .and(globMatches("*.png"))
            .and(regexContains("[-]"));

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(PATH, filter)) {
            assertThat(entries).contains(Paths.get(PATH + "/png-test.png"));
        }
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(PATH, filter)) {
            entries.forEach(System.out::println);
        }
    }

    @Test
    @DisplayName("Task3Test : .txt Test")
    void shouldFilterFilesAndFindTxt() throws IOException {
        DirectoryStream.Filter<Path> filter = REGULAR_FILE
            .and(READABLE)
            .and(WRITABLE)
            .and(largerThan(1))
            .and(lessThan(10000))
            .and(globMatches("*.txt"));

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(PATH, filter)) {
            assertThat(entries).contains(Paths.get(PATH + "/llllll.txt"));
        }
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(PATH, filter)) {
            entries.forEach(System.out::println);
        }
    }
}
