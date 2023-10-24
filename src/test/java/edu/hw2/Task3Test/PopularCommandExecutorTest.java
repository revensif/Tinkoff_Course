package edu.hw2.Task3Test;

import edu.hw2.Task3.ConnectionException;
import edu.hw2.Task3.ConnectionManager.DefaultConnectionManager;
import edu.hw2.Task3.ConnectionManager.FaultyConnectionManager;
import edu.hw2.Task3.PopularCommandExecutor;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class PopularCommandExecutorTest {
    @Test
    @DisplayName("Task3Test: PopularCommandExecutor(DefaultConnectionManager) Test")
    void popularCommandExecutorDefaultManagerTest() {
        Random rand = mock(Random.class);
        Mockito.when(rand.nextInt(0, 10)).thenReturn(2);
        PopularCommandExecutor
            executor = new PopularCommandExecutor(new DefaultConnectionManager(rand), 1);
        assertDoesNotThrow(executor::updatePackages);
    }

    @Test
    @DisplayName("Task3Test: FaultyConnectionManager(FaultyConnectionManager) Test")
    void popularCommandExecutorFaultyManagerTest() {
        Random rand = mock(Random.class);
        Mockito.when(rand.nextInt(0, 10)).thenReturn(0);
        PopularCommandExecutor executor =
            new PopularCommandExecutor(new FaultyConnectionManager(rand), 100);
        assertThrows(ConnectionException.class, executor::updatePackages);
    }
}
