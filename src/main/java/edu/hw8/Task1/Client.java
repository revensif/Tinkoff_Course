package edu.hw8.Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static edu.hw8.Task1.Constants.PORT;

public class Client {
    private static final Logger LOGGER = LogManager.getLogger();
    private Socket clientSocket;

    public void start() throws IOException {
        clientSocket = new Socket("localhost", PORT);
        LOGGER.info("Created a new client");
    }

    public String sendMessageToServerAndGetAnswer(String message) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)) {
            writer.println(message);
            String messageFromServer = reader.readLine();
            LOGGER.info("Сервер: {}", messageFromServer);
            clientSocket.close();
            return messageFromServer;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
