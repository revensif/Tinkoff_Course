package edu.hw2.Task3;

import edu.hw2.Task3.Connection.FaultyConnection;
import edu.hw2.Task3.Connection.StableConnection;
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
                return new StableConnection();
            } else {
                return new FaultyConnection(rand);
            }
        }
    }

    class FaultyConnectionManager implements ConnectionManager {
        private final Random rand;

        public FaultyConnectionManager(Random rand) {
            this.rand = rand;
        }

        @Override
        public Connection getConnection() {
            return new FaultyConnection(rand);
        }
    }
}
