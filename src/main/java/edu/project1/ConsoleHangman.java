package edu.project1;

import edu.project1.Dictionary.FullDictionary;
import edu.project1.GuessResult.Defeat;
import edu.project1.GuessResult.FailedGuess;
import edu.project1.GuessResult.Win;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleHangman {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String RIGHT_ARROW = ">";
    private static final String WORD = "> The word: ";
    private static final int MAX_ATTEMPTS = 5;
    private static final String PATTERN = "^[a-zA-Z]+$";

    public void run(Random rand) throws IOException {
        String word = new FullDictionary().randomWord(rand);
        if (word.length() < 2) {
            throw new IllegalArgumentException("Error: The length of the word must be at least 2 letters");
        }
        if (!word.matches(PATTERN)) {
            throw new IllegalArgumentException("Error: The word contains numbers");
        }
        Session session = new Session(word, MAX_ATTEMPTS);
        while (true) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
            LOGGER.info("> Guess a letter:\n");
            LOGGER.info("< ");
            String input = rd.readLine();
            GuessResult result = tryGuess(session, input);
            if ((result.getClass() == Win.class) || (result.getClass() == Defeat.class)) {
                printExit(result);
                break;
            }
            printState(result);
        }
    }

    GuessResult tryGuess(Session session, String input) {
        if (input.equals("give up")) {
            return session.giveUp();
        }
        if ((input.length() != 1) || (!input.matches(PATTERN))) {
            return new FailedGuess(
                session.getUserAnswer(),
                session.getAttempts(),
                session.getMaxAttempts(),
                " Incorrect input, try again!"
            );
        }
        if (session.getAttempts() >= session.getMaxAttempts()) {
            return new Defeat(session.getUserAnswer(), session.getAttempts(), session.getMaxAttempts());
        }
        return session.guess(input.toLowerCase().charAt(0));

    }

    private void printState(GuessResult guess) {
        LOGGER.info(RIGHT_ARROW + guess.message() + "\n");
        LOGGER.info(RIGHT_ARROW + "\n");
        LOGGER.info(WORD + new String(guess.state()) + "\n");
        LOGGER.info(RIGHT_ARROW + "\n");
    }

    private void printExit(GuessResult result) {
        if (result.getClass() == GuessResult.Win.class) {
            LOGGER.info("> Hit!\n");
        } else {
            LOGGER.info("> Missed, mistake " + MAX_ATTEMPTS + " out of " + MAX_ATTEMPTS + ".\n");
        }
        LOGGER.info(RIGHT_ARROW + "\n");
        LOGGER.info(WORD + new String(result.state()) + "\n");
        LOGGER.info(RIGHT_ARROW + "\n");
        LOGGER.info(RIGHT_ARROW + result.message() + "\n");
    }
}
