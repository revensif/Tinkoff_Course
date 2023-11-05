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
    private static final String WORD = "> Your answer: ";
    private static final int MAX_ATTEMPTS = 5;
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    static final String PATTERN = "^[a-zA-Z]+$";

    public void run(Random rand) {
        LOGGER.info("> If you want to give up, write" + RED + " 'give up' " + GREEN + "to the console.\n");
        String word = new FullDictionary(rand).randomWord();
        Session session = new Session(word, MAX_ATTEMPTS);
        try (BufferedReader rd = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                LOGGER.info("> Guess a letter:\n");
                LOGGER.info("< ");
                String input = rd.readLine();
                GuessResult result = tryGuess(session, input);
                if ((result instanceof Win) || (result instanceof Defeat)) {
                    printExit(result, session.getAnswer());
                    break;
                }
                printState(result);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
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
                " Incorrect input, try again! Correct Input - a,b,...,z"
            );
        }
        if (session.getAttempts() >= session.getMaxAttempts()) {
            return new Defeat(session.getUserAnswer(), session.getAttempts(), session.getMaxAttempts());
        }
        return session.guess(input.charAt(0));

    }

    private void printState(GuessResult guess) {
        LOGGER.info(RIGHT_ARROW + guess.message() + "\n");
        LOGGER.info(RIGHT_ARROW + "\n");
        LOGGER.info(WORD + new String(guess.state()) + "\n");
        LOGGER.info(RIGHT_ARROW + "\n");
    }

    private void printExit(GuessResult result, String word) {
        if (result.getClass() == GuessResult.Win.class) {
            LOGGER.info("> Hit!\n");
        } else {
            LOGGER.info("> Missed, mistake " + MAX_ATTEMPTS + " out of " + MAX_ATTEMPTS + ".\n");
            LOGGER.info(RIGHT_ARROW + "\n");
            LOGGER.info(RIGHT_ARROW + " The word : " + word + "\n");

        }
        LOGGER.info(RIGHT_ARROW + "\n");
        LOGGER.info(WORD + String.valueOf(result.state()) + "\n");
        LOGGER.info(RIGHT_ARROW + "\n");
        LOGGER.info(RIGHT_ARROW + result.message() + "\n");
    }
}
