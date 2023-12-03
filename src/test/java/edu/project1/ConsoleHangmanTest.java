package edu.project1;

import edu.project1.GuessResult.Defeat;
import edu.project1.GuessResult.FailedGuess;
import edu.project1.GuessResult.SuccessfulGuess;
import edu.project1.GuessResult.Win;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsoleHangmanTest {

    @Test
    @DisplayName("tryGuess() Test: Incorrect Test")
    void incorrectTryGuessTest() {
        String expected = " Incorrect input, try again! Correct Input - a,b,...,z";
        ConsoleHangman hangman = new ConsoleHangman();
        Session session = new Session("Test", 5);

        //Trying to enter a number instead of a letter
        GuessResult result = hangman.tryGuess(session, "1");
        assertEquals(FailedGuess.class, result.getClass());
        assertEquals(expected, result.message());

        //Attempt to enter an empty string
        result = hangman.tryGuess(session, "");
        assertEquals(FailedGuess.class, result.getClass());
        assertEquals(expected, result.message());

        //Attempt to enter a string that contains more than 1 letter
        result = hangman.tryGuess(session, "asd");
        assertEquals(FailedGuess.class, result.getClass());
        assertEquals(expected, result.message());

        //Attempt to enter a letter that has already been entered
        hangman.tryGuess(session, "a");
        result = hangman.tryGuess(session, "a");
        assertEquals(FailedGuess.class, result.getClass());
        assertEquals(" This guess has already been made!", result.message());

        //Attempt to enter a letter when the number of attempts has ended
        session = new Session("Test", 0);
        result = hangman.tryGuess(session, "a");
        assertEquals(Defeat.class, result.getClass());
        assertEquals(" You lost!", result.message());
    }

    @Test
    @DisplayName("tryGuess() Test: Correct Test (Win)")
    void correctTryGuessWinTest() {
        ConsoleHangman hangman = new ConsoleHangman();
        Session session = new Session("Test", 5);
        GuessResult result = hangman.tryGuess(session, "t");

        //Successful attempt to guess the letter from the word test
        assertEquals(SuccessfulGuess.class, result.getClass());
        assertEquals(" Hit!", result.message());
        assertEquals("t**t", new String(result.state()));

        //Checking that when you enter all the letters, class == Win
        hangman.tryGuess(session, "E");
        result = hangman.tryGuess(session, "s");
        assertEquals(Win.class, result.getClass());
        assertEquals(" You won!", result.message());
        assertEquals("test", new String(result.state()));
    }

    @Test
    @DisplayName("tryGuess() Test: Correct Test (Lost)")
    void correctTryGuessLostTest() {
        ConsoleHangman hangman = new ConsoleHangman();
        Session session = new Session("Test", 3);
        GuessResult result = hangman.tryGuess(session, "a");

        //Successful attempt to guess the letter from the word test
        assertEquals(FailedGuess.class, result.getClass());
        assertEquals(
            " Missed, mistake " + result.attempt() + " out of " + result.maxAttempts() + ".",
            result.message()
        );
        assertEquals("****", new String(result.state()));

        //Checking that when you enter all the letters, class == Lost
        hangman.tryGuess(session, "e");
        hangman.tryGuess(session, "l");
        result = hangman.tryGuess(session, "i");
        assertEquals(Defeat.class, result.getClass());
        assertEquals(" You lost!", result.message());
        assertEquals("*e**", new String(result.state()));
    }

    @Test
    @DisplayName("give up Test")
    void giveUpTest() {
        ConsoleHangman hangman = new ConsoleHangman();
        Session session = new Session("Test", 3);
        GuessResult result = hangman.tryGuess(session, "give up");
        assertEquals(Defeat.class, result.getClass());
        assertEquals(" You lost!", result.message());
        assertEquals("****", new String(result.state()));
    }
}
