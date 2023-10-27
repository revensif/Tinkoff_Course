package edu.hw3.Task5Test;

import edu.hw3.Task5.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PersonTest {

    @Test
    @DisplayName("Task5Test: Incorrect Input")
    void shouldCreatePersonAndThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new Person("12"));
        assertThrows(IllegalArgumentException.class, () -> new Person(")("));
    }
}
