package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {

    @ParameterizedTest
    @DisplayName("Task1Test: Correct Input")
    @CsvSource({
        "Hello world!, Svool dliow!",
        "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler, " +
            "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi"
    })
    void shouldDoAtbashText(String text, String totalExpected) {
        String totalCalculated = Task1.atbash(text);
        assertEquals(totalExpected, totalCalculated);
    }

    @ParameterizedTest
    @DisplayName("Task1Test: Incorrect Input")
    @CsvSource({
        "123, 123",
        "Тест, Тест",
        ", ''",
        "'', ''",
    })
    void shouldNotDoAtbashText(String text, String totalExpected) {
        String totalCalculated = Task1.atbash(text);
        assertEquals(totalExpected, totalCalculated);
    }
}
