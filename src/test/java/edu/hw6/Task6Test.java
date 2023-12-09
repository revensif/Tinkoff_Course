package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.ServerSocket;
import static edu.hw6.Task6.Task6.getUsedPorts;
import static org.assertj.core.api.Assertions.assertThat;

public class Task6Test {
    private static final int PORT = 18080;

    @Test
    @DisplayName("getUsedPorts() Test")
    void shouldGetUserPorts() {
        assertThat(getUsedPorts()).isNotEmpty();
        try (ServerSocket ignored = new ServerSocket(PORT)){
            assertThat(getUsedPorts()).contains(String.valueOf(PORT));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
