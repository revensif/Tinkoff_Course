package edu.hw2.Task4Test;

import edu.hw2.Task4.Task4;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw2.Task4.Task4.MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task4Test {
    @Test
    @DisplayName("Task4Test: Correct Input")
    void Task4TestCorrect() {
        assertEquals("callingInfo", Task4.callingInfo("callingInfo").methodName());
        assertEquals("edu.hw2.Task4.Task4", Task4.callingInfo("callingInfo").className());
    }

    @Test
    @DisplayName("Task4Test: Incorrect Input")
    void Task4TestIncorrect() {
        assertEquals(MESSAGE, Task4.callingInfo("").className());
        assertEquals(MESSAGE, Task4.callingInfo("something").className());
    }
}
