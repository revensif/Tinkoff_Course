package edu.hw6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw6.Task4.Task4.createFile;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {

    @Test
    @DisplayName("Task4Test : CorrectInput")
    void shouldCreateFileWithText() {
        Path path = Path.of("src\\main\\java\\edu\\hw6\\Task4\\Files\\Test.txt");
        if (path.toFile().exists()) {
            path.toFile().delete();
        }
        createFile(path);
        assertThat(path.toFile().exists()).isTrue();
        String textInFile = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            textInFile = reader.readLine();
        } catch (IOException ignored) {
        }
        assertThat(textInFile).isEqualTo("Programming is learned by writing programs. ― Brian Kernighan");
    }
}
