package edu.hw2.Task3Test;

import edu.hw2.Task3.Connection;
import edu.hw2.Task3.ConnectionManager;
import edu.hw2.Task3.ConnectionManager.DefaultConnectionManager;
import edu.hw2.Task3.ConnectionManager.FaultyConnectionManager;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class ConnectionManagersTest {
    @Test
    @DisplayName("Task3Test: DefaultConnectionManager(StableConnection) Test")
    void defaultManagerStableConnectionTest() {
        Random rand = mock(Random.class);
        Mockito.when(rand.nextInt(0, 10)).thenReturn(2);
        ConnectionManager defaultManager = new DefaultConnectionManager(rand);
        Connection connection = defaultManager.getConnection();
        assertThat(connection.getClass()).isEqualTo(Connection.StableConnection.class);
    }

    @Test
    @DisplayName("Task3Test: DefaultConnectionManager(FaultyConnection) Test")
    void defaultManagerFaultyConnectionTest() {
        Random rand = mock(Random.class);
        Mockito.when(rand.nextInt(0, 10)).thenReturn(0);
        ConnectionManager defaultManager = new DefaultConnectionManager(rand);
        Connection connection = defaultManager.getConnection();
        assertThat(connection.getClass()).isEqualTo(Connection.FaultyConnection.class);
    }

    @Test
    @DisplayName("Task3Test: FaultyConnectionManager Test")
    void faultyManagerTest() {
        Random rand = mock(Random.class);
        Mockito.when(rand.nextInt(0, 10)).thenReturn(0);
        ConnectionManager defaultManager = new FaultyConnectionManager(rand);
        Connection connection = defaultManager.getConnection();
        assertThat(connection.getClass()).isEqualTo(Connection.FaultyConnection.class);
        Mockito.when(rand.nextInt(0, 10)).thenReturn(2);
        connection = defaultManager.getConnection();
        assertThat(connection.getClass()).isEqualTo(Connection.FaultyConnection.class);
    }
}
