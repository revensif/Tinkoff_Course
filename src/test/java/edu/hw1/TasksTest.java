package edu.hw1;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TasksTest {
    @Nested
    class Task1Test {
        @ParameterizedTest
        @DisplayName("Task1Test: Correct Input")
        @CsvSource({
            "03:20, 200",
            "01:00, 60",
            "13:56, 836",
            "00:00, 0",
            "00100:00, 6000",
            "10:0003, 603"
        })
        void Task1TestCorrect(String time, Integer totalExpected) {
            int totalCalculated = Task1.minutesToSeconds(time);
            assertEquals(totalExpected, totalCalculated);
        }

        @ParameterizedTest
        @DisplayName("Task1Test: Incorrect input")
        @CsvSource({
            "3:01, -1",
            "09: 1, -1",
            ",-1",
            "10:1, -1",
            "10:60, -1",
            "'', -1",
            "ad:1a, -1",
            "-00600:00, -1",
            "12:nh, -1",
            "12:12:10, -1",
            "12:'', -1",
            "91:, -1",
            "09, -1",
            "102:-20, -1"
        })
        void Task1TestIncorrect(String time, Integer totalExpected) {
            int totalCalculated = Task1.minutesToSeconds(time);
            assertEquals(totalExpected, totalCalculated);
        }
    }

    @ParameterizedTest
    @DisplayName("Task2Test: Correct Input")
    @CsvSource({
        "4666, 4",
        "544, 3",
        "-10, 2",
        "0, 1",
        "85612, 5",
        "-17529548, 8",
    })
    void Task2Test(int num, int totalExpected) {
        int totalCalculated = Task2.countDigits(num);
        assertEquals(totalExpected, totalCalculated);
    }

    @Nested
    class Task3Test {
        @ParameterizedTest
        @DisplayName("Task3Test: Correct Input")
        @MethodSource("generateCorrectData")
        void Task3TestCorrect(int[] a1, int[] a2, boolean totalExpected) {
            boolean totalCalculated = Task3.isNestable(a1, a2);
            assertEquals(totalExpected, totalCalculated);
        }

        @ParameterizedTest
        @DisplayName("Task3Test: Incorrect Input")
        @MethodSource("generateIncorrectData")
        void Task3TestIncorrect(int[] a1, int[] a2, boolean totalExpected) {
            boolean totalCalculated = Task3.isNestable(a1, a2);
            assertEquals(totalExpected, totalCalculated);
        }

        static Stream<Arguments> generateCorrectData() {
            return Stream.of(
                Arguments.of(new int[] {1, 2, 3, 4}, new int[] {0, 6}, true),
                Arguments.of(new int[] {3, 1}, new int[] {4, 0}, true),
                Arguments.of(new int[] {9, 9, 8}, new int[] {8, 9}, false),
                Arguments.of(new int[] {1, 2, 3, 4}, new int[] {2, 3}, false),
                Arguments.of(new int[] {7, 11, 3, 5}, new int[] {9, 2, 6, 12, 4}, true)
            );
        }

        static Stream<Arguments> generateIncorrectData() {
            return Stream.of(
                Arguments.of(null, null, false),
                Arguments.of(null, new int[] {1, 2}, false),
                Arguments.of(new int[] {1, 2, 3, 5}, null, false)
            );
        }
    }

    @Nested
    class Tast4Test {
        @ParameterizedTest
        @DisplayName("Task4Test: Correct Input")
        @CsvSource({
            "123456, 214365",
            "hTsii  s aimex dpus rtni.g, This is a mixed up string.",
            "badce, abcde",
            "a, a",
            "etts, test"
        })
        void Task4TestCorrect(String str, String totalExpected) {
            String totalCalculated = Task4.fixString(str);
            assertEquals(totalExpected, totalCalculated);
        }

        @ParameterizedTest
        @DisplayName("Task4Test: Incorrect Input")
        @CsvSource({
            ", ''",
            "'', ''",
            "'   ', ''",
            "'      ', ''"
        })
        void Task4TestIncorrect(String str, String totalExpected) {
            String totalCalculated = Task4.fixString(str);
            assertEquals(totalExpected, totalCalculated);
        }
    }

    @ParameterizedTest
    @DisplayName("Task5Test: Correct Input")
    @CsvSource({
        "11211230, true",
        "13001120, true",
        "23336014, true",
        "11, true",
        "37956, true",
        "124, false",
        "7, false",
        "29375, false",
        "12030701, false"
    })
    void Task5Test(int num, boolean totalExpected) {
        boolean totalCalculated = Task5.isPalindromeDescendant(num);
        assertEquals(totalExpected, totalCalculated);
    }

    @Nested
    class Task6Test {
        @ParameterizedTest
        @DisplayName("Task6Test: Correct Input")
        @CsvSource({
            "6621, 5",
            "6554, 4",
            "1234, 3",
            "3524, 3",
            "6174, 0",
            "2221, 5"
        })
        void Task6TestCorrect(int num, int totalExpected) {
            int totalCalculated = Task6.countK(num);
            assertEquals(totalExpected, totalCalculated);
        }

        @ParameterizedTest
        @DisplayName("Task6Test: Incorrect Input")
        @CsvSource({
            "-1123, -1",
            "1000, -1",
            "10000, -1"
        })
        void Task6TestIncorrect(int num, int totalExpected) {
            int totalCalculated = Task6.countK(num);
            assertEquals(totalExpected, totalCalculated);
        }
    }

    @Nested
    class Task7Test {
        @ParameterizedTest
        @DisplayName("Task7Test: rotateRight()")
        @CsvSource({
            "8, 1, 4",
            "16, 1, 8",
            "5, 1, 6",
            "-8, -1, 1",
            "6, -2, 3",
            "0, 30, 0",
            "-21, 261, 26"
        })
        void Task7TestRight(int num, int shift, int totalExpected) {
            int totalCalculated = Task7.rotateRight(num, shift);
            assertEquals(totalExpected, totalCalculated);
        }

        @ParameterizedTest
        @DisplayName("Task7Test: rotateLeft()")
        @CsvSource({
            "8, 1, 1",
            "-16, 1, 1",
            "17, 2, 6",
            "-9, -2, 6",
            "11, -3, 7",
            "0, 12, 0",
            "29, 112, 23"
        })
        void Task7TestLeft(int num, int shift, int totalExpected) {
            int totalCalculated = Task7.rotateLeft(num, shift);
            assertEquals(totalExpected, totalCalculated);
        }
    }

    @Nested
    class Task8Test {
        @ParameterizedTest
        @DisplayName("Task8Test: Correct Input")
        @MethodSource("generateCorrectData")
        void Task8TestCorrect(int[][] board, boolean totalExpected) {
            boolean totalCalculated = Task8.knightBoardCapture(board);
            assertEquals(totalExpected, totalCalculated);
        }

        @ParameterizedTest
        @DisplayName("Task8Test: Incorrect Input")
        @MethodSource("generateIncorrectData")
        void Task8TestIncorrect(int[][] board, boolean totalExpected) {
            boolean totalCalculated = Task8.knightBoardCapture(board);
            assertEquals(totalExpected, totalCalculated);
        }

        static Stream<Arguments> generateCorrectData() {
            return Stream.of(
                Arguments.of(new int[][] {
                    {0, 0, 0, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 1, 0, 0, 0, 1, 0, 0},
                    {0, 0, 0, 0, 1, 0, 1, 0},
                    {0, 1, 0, 0, 0, 1, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 1, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 1, 0, 0, 0}}, true),
                Arguments.of(new int[][] {
                    {0, 0, 0, 1, 0, 0, 0, 0},
                    {1, 0, 0, 0, 1, 0, 0, 0},
                    {0, 1, 0, 0, 0, 1, 0, 0},
                    {1, 0, 0, 0, 1, 0, 1, 0},
                    {0, 1, 0, 1, 0, 1, 0, 0},
                    {0, 0, 0, 0, 1, 0, 0, 0},
                    {0, 1, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 1, 0, 0, 0}}, true),
                Arguments.of(new int[][] {
                    {1, 0, 1, 0, 1, 0, 1, 0},
                    {0, 1, 0, 1, 0, 1, 0, 1},
                    {0, 0, 0, 0, 1, 0, 1, 0},
                    {0, 0, 1, 0, 0, 1, 0, 1},
                    {1, 0, 0, 0, 1, 0, 1, 0},
                    {0, 0, 0, 0, 0, 1, 0, 1},
                    {1, 0, 0, 0, 1, 0, 1, 0},
                    {0, 0, 0, 1, 0, 1, 0, 1}}, false),
                Arguments.of(new int[][] {
                    {0, 0, 0, 0, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 1, 0, 0},
                    {0, 0, 0, 1, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 1, 0, 0},
                    {0, 0, 0, 0, 0, 1, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0}}, false)
            );
        }

        static Stream<Arguments> generateIncorrectData() {
            return Stream.of(
                Arguments.of(new int[][] {
                    {0, 0, 0, 0, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 1, 0, 0},
                    {0, 0, 0, 1, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 1, 0, 0},
                    {0, 0, 0, 0, 0, 1, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0}}, false),
                Arguments.of(new int[][] {
                    {0, 0, 0, 0, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 1, 0, 0},
                    {0, 0, 0, 1, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0}}, false),
                Arguments.of(null, false),
                Arguments.of(new int[][] {
                    {0, 0, 0, 0, 1, 0, 0, 0},
                    {0, 1, 0, 1, 0, 1, 0, 1},
                    {0, 0, 0, 1, 0, 0, 0, 0},
                    {1}}, false),
                Arguments.of(new int[][] {
                    {0, 0, 0, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 4, 0, 0, 0},
                    {0, 1, 2, 0, 0, 1, 0, 0},
                    {0, 0, 0, 0, 1, 0, 1, 0},
                    {0, 1, 0, 0, 0, 1, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 1, 0, 0, 5, 0, 0, 1},
                    {0, 0, 0, 0, 1, 0, 0, 0}}, false),
                Arguments.of(new int[][] {
                    {0, 0, 0, 1, 0, 0, 0, 0},
                    {},
                    {0, 0, 0, 1, 0, 0, 0, 0},
                    {0, 0, 0, 1, 0, 0, 0, 0},
                    {0, 0, 0, 1, 0, 0, 0, 0},
                    {},
                    {0, 0, 0, 1, 0, 0, 0, 0},
                    {0, 0, 0, 1, 0, 0, 0, 0}}, false)
            );
        }
    }
}
