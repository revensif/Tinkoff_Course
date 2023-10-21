package edu.hw2.Task3;

import java.util.Random;
import static edu.hw2.Task3.PopularCommandExecutor.MAX_NUM;

public interface ConnectionManager {
    Connection getConnection();

    class DefaultConnectionManager implements ConnectionManager {
        private final Random rand;

        public DefaultConnectionManager(Random rand) {
            this.rand = rand;
        }

        @Override
        public Connection getConnection() {
            if (rand.nextInt(0, MAX_NUM) >= 1) {
                return new Connection.StableConnection();
            } else {
                return new Connection.FaultyConnection();
            }
        }
    }

    class FaultyConnectionManager implements ConnectionManager {

        @Override
        public Connection getConnection() {
            return new Connection.FaultyConnection();
        }
    }
}
