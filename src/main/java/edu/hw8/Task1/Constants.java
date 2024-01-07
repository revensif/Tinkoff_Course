package edu.hw8.Task1;

import java.util.Map;
import static java.util.Map.entry;

public final class Constants {
    static final String ERROR_MESSAGE = "An error has occurred: {}";

    static final long TIMEOUT = 5L;
    static final int THREADS = 4;
    static final int PORT = 18080;
    static final String LOCALHOST = "localhost";
    static final Map<String, String> MAP = Map.ofEntries(
        entry("личности", "Не переходи на личности там, где их нет"),
        entry(
            "оскорбления",
            "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами"
        ),
        entry(
            "глупый",
            "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма."
        ),
        entry("интеллект", "Чем ниже интеллект, тем громче оскорбления")
    );

    private Constants() {
    }
}
