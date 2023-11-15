package edu.hw6;

import edu.hw6.Task2.Task2;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {

    @Test
    @DisplayName("Task2Test : Correct Input")
    void shouldCreateCopy() throws IOException {
        Path path = Path.of("src\\main\\java\\edu\\hw6\\Task2\\FilesCopy");
        File file = path.toFile();
        deleteFolder(file);
        Path copyPath = Path.of(path + "\\Tinkoff Bank Biggest Secret.txt");
        for (int i = 0; i < 4; i++) {
            Task2.cloneFile(copyPath);
        }
        File[] files = file.listFiles();
        assertThat(files.length).isEqualTo(4);
        assertThat(files).contains(copyPath.toFile());
        assertThat(files).contains(Path.of(path + "\\Tinkoff Bank Biggest Secret — копия.txt").toFile());
        assertThat(files).contains(Path.of(path + "\\Tinkoff Bank Biggest Secret — копия (2).txt").toFile());
        assertThat(files).contains(Path.of(path + "\\Tinkoff Bank Biggest Secret — копия (3).txt").toFile());
    }

    private static void deleteFolder(File file) {
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                f.delete();
            }
        }
        file.delete();
        file.mkdirs();
    }
}

