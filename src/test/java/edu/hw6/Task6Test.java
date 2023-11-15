package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw6.Task6.Task6.getUsedPorts;
import static org.assertj.core.api.Assertions.assertThat;

public class Task6Test {

    @Test
    @DisplayName("getUsedPorts() Test")
    void shouldGetUserPorts() {
        assertThat(getUsedPorts()).isNotEmpty();
        assertThat(getUsedPorts()).contains("135");
    }
}
