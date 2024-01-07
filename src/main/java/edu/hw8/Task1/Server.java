package edu.hw8.Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static edu.hw8.Task1.Constants.ERROR_MESSAGE;
import static edu.hw8.Task1.Constants.MAP;
import static edu.hw8.Task1.Constants.PORT;
import static edu.hw8.Task1.Constants.THREADS;
import static edu.hw8.Task1.Constants.TIMEOUT;

public class Server {
    private final ExecutorService executor = Executors.newFixedThreadPool(THREADS);
    private int responseNumber = 1;
    private static final Logger LOGGER = LogManager.getLogger();

    public Server(int responseNumber) {
        if (responseNumber < 1) {
            LOGGER.error("Wrong number of responses - {}", responseNumber);
            throw new IllegalArgumentException("Number of response should be positive");
        }
        this.responseNumber = responseNumber;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (responseNumber > 0) {
                Socket client = serverSocket.accept();
                responseNumber--;
                executor.submit(() -> handleClient(client));
            }
            executor.awaitTermination(TIMEOUT, TimeUnit.SECONDS);
        } catch (Exception e) {
            LOGGER.error(ERROR_MESSAGE, e.getMessage());
            throw new RuntimeException(e);
        }
        executor.close();
    }

    private void handleClient(Socket client) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
             PrintWriter writer = new PrintWriter(client.getOutputStream(), true)) {
            String messageFromClient = reader.readLine();
            LOGGER.info("Клиент: {}", messageFromClient);
            String messageToClient = MAP.get(messageFromClient);
            writer.println(messageToClient);
            LOGGER.info("Сервер: {}", messageToClient);
        } catch (IOException e) {
            LOGGER.error(ERROR_MESSAGE, e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
