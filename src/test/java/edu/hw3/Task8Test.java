package edu.hw3;

import edu.hw3.Task8.BackwardIterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task8Test {

    @Test
    @DisplayName("Task8Test: Correct Input")
    void shouldDoCreateBackwardIterator() {
        BackwardIterator<Integer> iterator = new BackwardIterator<>(List.of(1, 2, 3));
        assertThat(iterator.hasNext()).isEqualTo(true);
        assertThat(iterator.next()).isEqualTo(3);
        assertThat(iterator.hasNext()).isEqualTo(true);
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.hasNext()).isEqualTo(true);
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.hasNext()).isEqualTo(false);
        assertThrows(NoSuchElementException.class, iterator::next);
    }
}
