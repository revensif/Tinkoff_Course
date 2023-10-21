package edu.hw2.Task3;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static edu.hw2.Task3.PopularCommandExecutor.MAX_NUM;

public interface Connection extends AutoCloseable {
    Logger LOGGER = LogManager.getLogger();

    void execute(String command, Random rand);

    class StableConnection implements Connection {

        @Override
        public void execute(String command, Random rand) {
            LOGGER.info(command);
        }

        @Override
        public void close() {
            LOGGER.info("Стабильное соединение закрыто");
        }
    }

    class FaultyConnection implements Connection {

        @Override
        public void execute(String command, Random rand) {
            if (rand.nextInt(0, MAX_NUM) >= 1) {
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
}
