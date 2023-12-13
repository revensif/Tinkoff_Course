package edu.hw8.Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static edu.hw8.Task1.Utils.LOCALHOST;
import static edu.hw8.Task1.Utils.PORT;

public class Client {
    private static final Logger LOGGER = LogManager.getLogger();
    private Socket clientSocket;

    public Client() {
        start();
    }

    private void start() {
        try {
            clientSocket = new Socket(LOCALHOST, PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String sendMessageToServerAndGetAnswer(String message) {
        if (clientSocket.isClosed()) {
            start();
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)) {
            writer.println(message);
            String messageFromServer = reader.readLine();
            LOGGER.info("Сервер: {}", messageFromServer);
            return messageFromServer;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeClient() {
        try {
            clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
