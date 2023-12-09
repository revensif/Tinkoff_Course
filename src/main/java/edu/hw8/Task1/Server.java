package edu.hw8.Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static edu.hw8.Task1.Utils.ERROR_MESSAGE;
import static edu.hw8.Task1.Utils.MAP;
import static edu.hw8.Task1.Utils.PORT;
import static edu.hw8.Task1.Utils.THREADS;

public class Server {
    private final ExecutorService executor = Executors.newFixedThreadPool(THREADS);
    private boolean serverStatus = true;
    private static final Logger LOGGER = LogManager.getLogger();

    public Server() {
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (serverStatus) {
                Socket client = serverSocket.accept();
                executor.submit(() -> handleClient(client));
            }
        } catch (IOException e) {
            LOGGER.error(ERROR_MESSAGE, e.getMessage());
            throw new RuntimeException(e);
        }
        closeServer();
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

    public void closeServer() {
        serverStatus = false;
    }
}
