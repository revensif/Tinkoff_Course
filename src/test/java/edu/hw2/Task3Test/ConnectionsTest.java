package edu.hw2.Task3Test;

import edu.hw2.Task3.Connection;
import edu.hw2.Task3.Connection.FaultyConnection;
import edu.hw2.Task3.Connection.StableConnection;
import edu.hw2.Task3.ConnectionException;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static edu.hw2.Task3.PopularCommandExecutor.COMMAND;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class ConnectionsTest {

    @Test
    @DisplayName("Task3Test: StableConnection Test")
    void stableConnectionTest() {
        Random rand = mock(Random.class);
        Mockito.when(rand.nextInt(0, 10)).thenReturn(0);
        try (Connection connection = new StableConnection()) {
            assertDoesNotThrow(() -> connection.execute(COMMAND));
        } catch (Exception ignored) {
        }
    }

    @Test
    @DisplayName("Task3Test: FaultyConnection Test")
    void faultyConnectionTest() {
        Random rand = mock(Random.class);
        Mockito.when(rand.nextInt(0, 10)).thenReturn(0);
        try (Connection connection = new FaultyConnection(rand)) {
            assertThrows(ConnectionException.class, () -> connection.execute(COMMAND));
        } catch (Exception ignored) {
        }
    }
}
