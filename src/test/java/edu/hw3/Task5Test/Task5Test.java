package edu.hw3.Task5Test;

import edu.hw3.Task5.Person;
import edu.hw3.Task5.Task5;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task5Test {

    @ParameterizedTest
    @DisplayName("Task5Test: Correct Input")
    @MethodSource("lists")
    void shouldDoParseContacts(List<Person> list, String sort, List<Person> totalExpected) {
        List<Person> totalCalculated = Task5.parseContacts(list, sort);
        assertThat(totalCalculated).isEqualTo(totalExpected);
    }

    static Arguments[] lists() {
        return new Arguments[] {
            Arguments.of(
                Arrays.asList(
                    new Person("John Locke"),
                    new Person("Thomas Aquinas"),
                    new Person("David Hume"),
                    new Person("Rene Descartes")
                ),
                "ASC",
                Arrays.asList(
                    new Person("Thomas Aquinas"),
                    new Person("Rene Descartes"),
                    new Person("David Hume"),
                    new Person("John Locke")
                )
            ),
            Arguments.of(
                Arrays.asList(
                    new Person("Paul Erdos"),
                    new Person("Leonhard Euler"),
                    new Person("Carl Gauss")
                ),
                "DESC",
                Arrays.asList(
                    new Person("Carl Gauss"),
                    new Person("Leonhard Euler"),
                    new Person("Paul Erdos")
                )
            ),
        };
    }

    @Test
    @DisplayName("Task5Test: Incorrect Input")
    void shouldDoParseContactsAndReturnEmptyList() {
        List<Person> list = new ArrayList<>();
        assertThat(Task5.parseContacts(list, "ASC")).isEqualTo(List.of());
        assertThat(Task5.parseContacts(null, "ASC")).isEqualTo(List.of());
        list.add(new Person(null));
        assertThat(Task5.parseContacts(list, "DESC")).isEqualTo(List.of());
        list.remove(0);
        list.add(new Person("Test"));
        assertThat(Task5.parseContacts(list, null)).isEqualTo(List.of());
        list.add(null);
        assertThat(Task5.parseContacts(list, "DESC")).isEqualTo(List.of());
    }

    @Test
    @DisplayName("Task5Test: Incorrect (Exception) Input")
    void shouldDoParseContactsAndThrowException() {
        List<Person> list = new ArrayList<>();
        list.add(new Person("test"));
        assertThrows(IllegalArgumentException.class, () -> Task5.parseContacts(list, "2"));
        assertThrows(IllegalArgumentException.class, () -> Task5.parseContacts(list, "AAAA"));
    }
}
