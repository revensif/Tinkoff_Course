package edu.project1;

import java.util.Random;
import org.jetbrains.annotations.NotNull;

interface Dictionary {
    @NotNull String randomWord(Random rand);

    class FullDictionary implements Dictionary {
        private static final String[] WORDS = {"heLlO", "uptown", "123", "a"};

        //Random был добавлен в качестве параметра для того, чтобы было удобно тестировать
        @Override
        public @NotNull String randomWord(Random rand) {
            return WORDS[rand.nextInt(0, WORDS.length)];
        }
    }
}
