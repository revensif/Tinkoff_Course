package edu.hw2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static edu.hw2.Task1.Expr.Addition;
import static edu.hw2.Task1.Expr.Constant;
import static edu.hw2.Task1.Expr.Exponent;
import static edu.hw2.Task1.Expr.Multiplication;
import static edu.hw2.Task1.Expr.Negate;
import static edu.hw2.Task2.Rectangle;
import static edu.hw2.Task2.Square;
import static edu.hw2.Task3.COMMAND;
import static edu.hw2.Task3.Connection;
import static edu.hw2.Task3.ConnectionException;
import static edu.hw2.Task3.DefaultConnectionManager;
import static edu.hw2.Task3.FaultyConnection;
import static edu.hw2.Task3.FaultyConnectionManager;
import static edu.hw2.Task3.PopularCommandExecutor;
import static edu.hw2.Task3.StableConnection;
import static edu.hw2.Task4.MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TasksTest {
    @Test
    @DisplayName("Task1Test: Correct Input")
    void Task1Test() {
        var two = new Constant(2);
        var four = new Constant(4);
        var negOne = new Negate(new Constant(1));
        var sumTwoFour = new Addition(two, four);
        var mult = new Multiplication(sumTwoFour, negOne);
        var exp = new Exponent(mult, 2);
        var res = new Addition(exp, new Constant(1));
        System.out.println(res + " = " + res.evaluate());
    }

    @ParameterizedTest
    @DisplayName("Task2Test: Correct Input")
    @MethodSource("rectangles")
    void Task2Test(Rectangle rect) {
        rect = rect.setWidth(20);
        rect = rect.setHeight(10);
        assertThat(rect.area()).isEqualTo(200.0);
    }

    static Arguments[] rectangles() {
        return new Arguments[] {
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        };
    }

    @Nested
    class Task3Test {
        @Test
        @DisplayName("Task3Test: DefaultConnectionManager Test")
        void defaultManagerTest() {
            PopularCommandExecutor executor = new PopularCommandExecutor(new DefaultConnectionManager(), 1000);
            assertDoesNotThrow(executor::updatePackages);
        }

        @Test
        @DisplayName("Task3Test: FaultyConnectionManager Test")
        void faultyManagerTest() {
            PopularCommandExecutor executor = new PopularCommandExecutor(new FaultyConnectionManager(), 1);
            assertThrows(ConnectionException.class, () -> {
                for (int i = 0; i < 10; i++) {
                    executor.updatePackages();
                }
            });
        }

        @Test
        @DisplayName("Task3Test: StableConnection Test")
        void stableConnectionTest() {
            int i;
            Connection connection = new StableConnection();
            for (i = 0; i < 1000; i++) {
                connection.execute(COMMAND);
            }
            assertThat(i).isEqualTo(1000);
        }

        @Test
        @DisplayName("Task3Test: FaultyConnection Test")
        void faultyConnectionTest() {
            Connection connection = new FaultyConnection();
            assertThrows(ConnectionException.class, () -> {
                for (int i = 0; i < 100; i++) {
                    connection.execute(COMMAND);
                }
            });
        }
    }

    @Nested
    class Task4Test {
        @Test
        @DisplayName("Task4Test: Correct Input")
        void Task4TestCorrect() {
            assertEquals("callingInfo", Task4.callingInfo("callingInfo").methodName());
            assertEquals("edu.hw2.Task4", Task4.callingInfo("callingInfo").className());
        }

        @Test
        @DisplayName("Task4Test: Incorrect Input")
        void Task4TestIncorrect() {
            assertEquals(MESSAGE, Task4.callingInfo("").className());
            assertEquals(MESSAGE, Task4.callingInfo("something").className());
        }
    }
}
