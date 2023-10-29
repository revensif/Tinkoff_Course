package edu.project1;

import edu.project1.GuessResult.Defeat;
import edu.project1.GuessResult.FailedGuess;
import edu.project1.GuessResult.SuccessfulGuess;
import edu.project1.GuessResult.Win;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public class Session {
    private static final String MESSAGE = " This guess has already been made!";
    private static final String MISSED = " Missed, mistake ";
    private static final String OUT = " out of ";
    private final String answer;
    private final char[] userAnswer;
    private final int maxAttempts;
    private int attempts = 0;
    private final Set<Character> set = new HashSet<>();

    Session(String answer, int maxAttempts) {
        this.answer = answer.toLowerCase();
        this.maxAttempts = maxAttempts;
        userAnswer = new char[answer.length()];
        Arrays.fill(userAnswer, '*');
    }

    public char[] getUserAnswer() {
        return userAnswer;
    }

    public int getAttempts() {
        return attempts;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    @NotNull GuessResult guess(char guess) {
        char b = Character.toLowerCase(guess);
        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == b) {
                userAnswer[i] = b;
            }
        }
        if (answer.indexOf(b) == -1) {
            return checkFailed(b);
        } else {
            return checkSuccessful(b);
        }
    }

    @NotNull GuessResult giveUp() {
        return new Defeat(userAnswer, attempts, maxAttempts);
    }

    private GuessResult checkFailed(char guess) {
        if (set.contains(guess)) {
            return new FailedGuess(userAnswer, attempts, maxAttempts, MESSAGE);
        } else {
            attempts++;
            if (attempts == maxAttempts) {
                return new Defeat(userAnswer, attempts, maxAttempts);
            }
            set.add(guess);
            return new FailedGuess(
                userAnswer,
                attempts,
                maxAttempts,
                MISSED + attempts + OUT + maxAttempts + "."
            );
        }
    }

    private GuessResult checkSuccessful(char guess) {
        if (set.contains(guess)) {
            return new SuccessfulGuess(
                userAnswer,
                attempts,
                maxAttempts,
                MESSAGE
            );
        } else {
            if (new String(userAnswer).equals(answer)) {
                return new Win(userAnswer, attempts, maxAttempts);
            }
            set.add(guess);
            return new SuccessfulGuess(
                userAnswer,
                attempts,
                maxAttempts,
                " Hit!"
            );
        }
    }
}
