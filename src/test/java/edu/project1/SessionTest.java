package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SessionTest {

    @Test
    @DisplayName("guess() Test: CorrectGuess and Win Test")
    void correctGuessTest() {
        Session session = new Session("Test", 5);

        GuessResult result = session.guess('t');
        assertEquals(GuessResult.SuccessfulGuess.class, result.getClass());
        assertEquals("t**t", String.valueOf(session.getUserAnswer()));
        assertEquals(0, session.getAttempts());

        result = session.guess('E');
        assertEquals(GuessResult.SuccessfulGuess.class, result.getClass());
        assertEquals("te*t", String.valueOf(session.getUserAnswer()));
        assertEquals(0, session.getAttempts());

        result = session.guess('e');
        assertEquals(GuessResult.SuccessfulGuess.class, result.getClass());
        assertEquals("te*t", String.valueOf(session.getUserAnswer()));
        assertEquals(0, session.getAttempts());

        result = session.guess('s');
        assertEquals(GuessResult.Win.class, result.getClass());
        assertEquals("test", String.valueOf(session.getUserAnswer()));
        assertEquals(0, session.getAttempts());
    }

    @Test
    @DisplayName("guess() Test: FailedGuess and Defeat Test")
    void failedGuessTest() {
        Session session = new Session("Test", 5);

        GuessResult result = session.guess('t');
        assertEquals(GuessResult.SuccessfulGuess.class, result.getClass());
        assertEquals("t**t", String.valueOf(session.getUserAnswer()));
        assertEquals(0, session.getAttempts());

        result = session.guess('a');
        assertEquals(GuessResult.FailedGuess.class, result.getClass());
        assertEquals("t**t", String.valueOf(session.getUserAnswer()));
        assertEquals(1, session.getAttempts());

        result = session.guess('A');
        assertEquals(GuessResult.FailedGuess.class, result.getClass());
        assertEquals("t**t", String.valueOf(session.getUserAnswer()));
        assertEquals(1, session.getAttempts());

        result = session.guess('b');
        assertEquals(GuessResult.FailedGuess.class, result.getClass());
        assertEquals("t**t", String.valueOf(session.getUserAnswer()));
        assertEquals(2, session.getAttempts());

        result = session.guess('c');
        assertEquals(GuessResult.FailedGuess.class, result.getClass());
        assertEquals("t**t", String.valueOf(session.getUserAnswer()));
        assertEquals(3, session.getAttempts());

        result = session.guess('d');
        assertEquals(GuessResult.FailedGuess.class, result.getClass());
        assertEquals("t**t", String.valueOf(session.getUserAnswer()));
        assertEquals(4, session.getAttempts());

        result = session.guess('k');
        assertEquals(GuessResult.Defeat.class, result.getClass());
        assertEquals("t**t", String.valueOf(session.getUserAnswer()));
        assertEquals(5, session.getAttempts());
    }

    @Test
    @DisplayName("giveUp() Test: Defeat Test")
    void sessionGiveUpTest() {
        Session session = new Session("test", 5);
        session.guess('a');
        GuessResult result = session.giveUp();
        assertEquals(GuessResult.Defeat.class, result.getClass());
        assertEquals(1, session.getAttempts());
    }
}
