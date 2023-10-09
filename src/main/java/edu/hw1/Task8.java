package edu.hw1;

import java.util.stream.IntStream;

public final class Task8 {
    private final static int N = 8;
    private final static int[] DX = {-1, 1, -2, 2, -2, 2, -1, 1};
    private final static int[] DY = {-2, -2, -1, -1, 1, 1, 2, 2};

    private Task8() {
    }

    public static boolean knightBoardCapture(int[][] board) {
        boolean flag = false;
        if (board == null || board.length != N) {
            return false;
        }
        for (int[] row : board) {
            if (row.length != N) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            flag = checkValues(board);
        }
        if (flag) {
            return false;
        }
        return canCapture(board);
    }

    private static boolean checkValues(int[][] board) {
        for (int i = 0; i < N; i++) {
            if (IntStream.of(board[i]).anyMatch(x -> ((x != 1) && (x != 0)))) {
                return true;
            }
        }
        return false;
    }

    private static boolean canCapture(int[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0) {
                    continue;
                }
                for (int k = 0; k < N; k++) {
                    int x = i + DX[k];
                    int y = j + DY[k];
                    if ((x < 0) || (y < 0) || (x >= N) || (y >= N)) {
                        continue;
                    }
                    if (board[x][y] == 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
