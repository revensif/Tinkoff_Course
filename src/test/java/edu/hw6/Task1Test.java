package edu.hw6;

import edu.hw6.Task1.DiskMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {

    @Test
    @DisplayName("put() Test : Correct Input")
    void shouldPutKeyAndValueInFile() throws IOException {
        DiskMap diskMap1 = new DiskMap("DiskMap1");
        DiskMap diskMap2 = new DiskMap("DiskMap2");
        diskMap1.put("1", "2");
        diskMap1.put("1", "3");
        diskMap1.put("test", "12");
        diskMap1.put("key", "value");
        diskMap2.put("1", "4");
        diskMap2.put("test", "13");
        diskMap2.put(null, "test");
        diskMap2.put("null", null);
        assertThat(diskMap1.size()).isEqualTo(3);
        assertThat(diskMap2.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("get() Test : Correct Input")
    void shouldGetValueInFile() {
        DiskMap diskMap3 = new DiskMap("DiskMap3");
        DiskMap diskMap4 = new DiskMap("DiskMap4");
        diskMap3.put("Tinkoff", "Course");
        diskMap3.put("Tinkoff", "Course2");
        diskMap3.put("Test", "№1");
        diskMap4.put("Asdasd", "123jmkl");
        diskMap4.put("Tinkoff", "");
        diskMap4.put("null", null);
        diskMap4.put(null, "test");
        assertThat(diskMap3.get("Tinkoff")).isEqualTo("Course2");
        assertThat(diskMap3.get("Test")).isEqualTo("№1");
        assertThat(diskMap3.get("Asdasd")).isNull();
        assertThat(diskMap4.get("Tinkoff")).isEqualTo("");
        assertThat(diskMap4.get(null)).isEqualTo(null);
    }
}
