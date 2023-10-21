package edu.hw2.Task3;

import java.util.Random;

public final class PopularCommandExecutor {
    public static final String COMMAND = "apt update && apt upgrade -y";
    private final ConnectionManager manager;
    private final int maxAttempts;
    static final int MAX_NUM = 10;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.maxAttempts = maxAttempts;
        this.manager = manager;
    }

    public void updatePackages(Random rand) {
        tryExecute(COMMAND, rand);
    }

    void tryExecute(String command, Random rand) {
        for (int i = 1; i <= maxAttempts; i++) {
            try (Connection connection = manager.getConnection()) {
                connection.execute(command, rand);
                break;
            } catch (Exception conExp) {
                if (i == maxAttempts) {
                    throw new ConnectionException("Все попытки подключения закончились", conExp.getCause());
                }
            }
        }
    }
}

