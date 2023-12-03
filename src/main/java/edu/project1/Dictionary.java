package edu.project1;

import java.util.Random;
import org.jetbrains.annotations.NotNull;
import static edu.project1.ConsoleHangman.PATTERN;

interface Dictionary {
    @NotNull String randomWord();

    class FullDictionary implements Dictionary {
        private final Random rand;
        private static final String[] WORDS = {"heLlO", "uptown", "123", "a"};
        private static final String BACKUP_WORD = "backup";

        FullDictionary(Random rand) {
            this.rand = rand;
        }

        @Override
        public @NotNull String randomWord() {
            String word = WORDS[rand.nextInt(0, WORDS.length)];
            if ((word.length() < 2) || (!word.matches(PATTERN))) {
                return BACKUP_WORD;
            }
            return word;
        }
    }
}
