package edu.hw2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task3 {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int ATTEMPTS = 5;
    private static final double CHANCE = 0.1;
    static final String COMMAND = "apt update && apt upgrade -y";

    private Task3() {
    }

    public interface Connection extends AutoCloseable {
        void execute(String command);
    }

    public interface ConnectionManager {
        Connection getConnection();
    }

    public static class DefaultConnectionManager implements ConnectionManager {

        @Override
        public Connection getConnection() {
            if (Math.random() > CHANCE) {
                return new StableConnection();
            } else {
                return new FaultyConnection();
            }
        }
    }

    public static class FaultyConnectionManager implements ConnectionManager {

        @Override
        public Connection getConnection() {
            return new FaultyConnection();
        }
    }

    public static class ConnectionException extends RuntimeException {
        private final String text;
        private final Throwable cause;

        public ConnectionException() {
            text = "";
            cause = new Exception();
        }

        public ConnectionException(String text, Throwable cause) {
            this.text = text;
            this.cause = cause;
        }
    }

    public static class FaultyConnection implements Connection {

        @Override
        public void execute(String command) {
            if (Math.random() > 1 - CHANCE) {
                LOGGER.info(command);
            } else {
                throw new ConnectionException();
            }
        }

        @Override
        public void close() {
            LOGGER.info("Дефектное соединение закрыто");
        }
    }

    public static class StableConnection implements Connection {

        @Override
        public void execute(String command) {
            LOGGER.info(command);
        }

        @Override
        public void close() {
            LOGGER.info("Стабильное соединение закрыто");
        }
    }

    public static final class PopularCommandExecutor {
        private final ConnectionManager manager;
        private final int maxAttempts;

        public PopularCommandExecutor() {
            manager = new DefaultConnectionManager();
            maxAttempts = ATTEMPTS;
        }

        public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
            this.maxAttempts = maxAttempts;
            this.manager = manager;
        }

        public void updatePackages() {
            tryExecute(COMMAND);
        }

        void tryExecute(String command) {
            Connection connection = manager.getConnection();
            for (int i = 1; i <= maxAttempts; i++) {
                try {
                    connection.execute(command);
                    break;
                } catch (ConnectionException conExp) {
                    if (i == maxAttempts) {
                        throw new ConnectionException("Все попытки подключения закончились", conExp.getCause());
                    }
                }
            }
            try {
                connection.close();
            } catch (Exception e) {
                throw new ConnectionException();
            }
        }
    }
}
