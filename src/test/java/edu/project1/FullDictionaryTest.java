package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Random;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class FullDictionaryTest {

    @Test
    @DisplayName("randomWord() Test: Incorrect Test")
    void incorrectRandomWordTest() {
        String backup = "backup";
        Random rand = mock(Random.class);
        Mockito.when(rand.nextInt(0, 4)).thenReturn(2);
        Dictionary dictionary = new Dictionary.FullDictionary(rand);
        assertThat(dictionary.randomWord()).isEqualTo(backup);
        Mockito.when(rand.nextInt(0, 4)).thenReturn(3);
        assertThat(dictionary.randomWord()).isEqualTo(backup);
    }

    @Test
    @DisplayName("randomWord() Test: Correct Test")
    void correctRandomWordTest() {
        Random rand = mock(Random.class);
        Mockito.when(rand.nextInt(0, 4)).thenReturn(0);
        Dictionary dictionary = new Dictionary.FullDictionary(rand);
        assertThat(dictionary.randomWord()).isEqualTo("heLlO");
        Mockito.when(rand.nextInt(0, 4)).thenReturn(1);
        assertThat(dictionary.randomWord()).isEqualTo("uptown");
    }
}
