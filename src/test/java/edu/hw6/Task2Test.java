package edu.hw6;

import edu.hw6.Task2.Task2;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    private static final Path FILE = Paths.get("src/main/java/edu/hw6/Task2/FilesCopy/Tinkoff Bank Biggest Secret.txt");
    private static final Path COPY1 =
        Paths.get("src/main/java/edu/hw6/Task2/FilesCopy/Tinkoff Bank Biggest Secret — копия.txt");
    private static final Path COPY2 =
        Paths.get("src/main/java/edu/hw6/Task2/FilesCopy/Tinkoff Bank Biggest Secret — копия (2).txt");

    @Test
    @DisplayName("Task2Test : Correct Input")
    void shouldCreateCopy() throws IOException {
        Path path = Path.of("src/main/java/edu/hw6/Task2/FilesCopy");
        File file = path.toFile();
        deleteFolder(file);
        Path copyPath = Path.of(path + "/Tinkoff Bank Biggest Secret.txt");
        for (int i = 0; i < 3; i++) {
            Task2.cloneFile(copyPath);
        }
        assertThat(Files.exists(FILE)).isTrue();
        assertThat(Files.exists(COPY1)).isTrue();
        assertThat(Files.exists(COPY2)).isTrue();
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

