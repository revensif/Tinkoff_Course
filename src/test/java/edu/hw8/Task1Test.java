package edu.hw8;

import edu.hw8.Task1.Client;
import edu.hw8.Task1.Server;
import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {
    private static final int SLEEP_NUMBER = 400;

    @Test
    @DisplayName("ResponseTest : One Client")
    void shouldGetResponseFromServerFor1Client() throws IOException, InterruptedException {
        String expected = "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами";
        Thread serverThread = new Thread(() -> {
            Server server = new Server(1);
            server.start();
        });
        serverThread.start();
        Thread.sleep(SLEEP_NUMBER);
        Client client = new Client();
        client.start();
        String actual = client.sendMessageToServerAndGetAnswer("оскорбления");
        serverThread.join();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("ResponseTest : Three Clients")
    void shouldGetResponsesFromServerFor3Client() throws InterruptedException {
        String[] expected = new String[] {"Чем ниже интеллект, тем громче оскорбления",
            "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
            "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма."};
        Thread serverThread = new Thread(() -> {
            Server server = new Server(3);
            server.start();
        });
        serverThread.start();
        Thread.sleep(SLEEP_NUMBER);
        Thread[] clients = new Thread[3];
        String[] messages = new String[] {"интеллект", "оскорбления", "глупый"};
        String[] actual = new String[3];
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            clients[i] = new Thread(() -> {
                Client client = new Client();
                try {
                    client.start();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                String response = client.sendMessageToServerAndGetAnswer(messages[finalI]);
                actual[finalI] = response;
            });
        }
        for (Thread client : clients) {
            client.start();
        }
        for (Thread client : clients) {
            client.join();
        }
        serverThread.join();
        assertThat(actual).containsExactly(expected);
    }
}
