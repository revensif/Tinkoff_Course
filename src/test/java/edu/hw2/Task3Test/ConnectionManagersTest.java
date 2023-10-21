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

public class ConnectionManagersTest {
    @Test
    @DisplayName("Task3Test: DefaultConnectionManager Test")
    void defaultManagerTest() {
        Random rand = mock(Random.class);
        Mockito.when(rand.nextInt(0, 10)).thenReturn(2);
        PopularCommandExecutor
            executor = new PopularCommandExecutor(new DefaultConnectionManager(rand), 1);
        assertDoesNotThrow(() -> executor.updatePackages(rand));
    }

    @Test
    @DisplayName("Task3Test: FaultyConnectionManager Test")
    void faultyManagerTest() {
        Random rand = mock(Random.class);
        Mockito.when(rand.nextInt(0, 10)).thenReturn(0);
        PopularCommandExecutor executor =
            new PopularCommandExecutor(new FaultyConnectionManager(), 100);
        assertThrows(ConnectionException.class, () -> executor.updatePackages(rand));
    }

}
