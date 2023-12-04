package edu.hw9;

import edu.hw9.Task2.DirectoriesFinder;
import edu.hw9.Task2.FilesFinder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task2Test {
    private static final Path path = Paths.get("src/main/java/edu/hw9/Task2/Files");

    @Test
    @DisplayName("FilesFinder Test : Correct Input")
    void shouldFindAllCorrectFiles() {
        FilesFinder filesFinder = new FilesFinder(path, 5, 200, "txt");
        List<Path> list = filesFinder.compute();
        assertThat(list.size()).isEqualTo(1);
        assertThat(list).containsExactly(Paths.get(path + "/secondDir/asdas.txt"));
    }

    @Test
    @DisplayName("FilesFinder Test : Incorrect Input")
    void shouldFindAllCorrectFilesAndThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new FilesFinder(path, 10, 0, "txt"));
        assertThrows(IllegalArgumentException.class, () -> new FilesFinder(null, 10, 100, "txt"));
        assertThrows(
            IllegalArgumentException.class,
            () -> new FilesFinder(Paths.get(path + "aksdmaksd"), 10, 100, "txt")
        );
    }

    @Test
    @DisplayName("DirectoriesFinder Test : Correct Input")
    void shouldFindAllCorrectDirectories() {
        DirectoriesFinder directoriesFinder = new DirectoriesFinder(path, 3);
        List<Path> list = directoriesFinder.compute();
        assertThat(list.size()).isEqualTo(1);
        assertThat(list).containsExactly(path);
    }

    @Test
    @DisplayName("DirectoriesFinder Test : Incorrect Input")
    void shouldFindAllCorrectDirectoriesAndThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new DirectoriesFinder(null, 10));
        assertThrows(IllegalArgumentException.class, () -> new DirectoriesFinder(Paths.get(path + "aksdmaksd"), 2));
    }
}
