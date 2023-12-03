package edu.hw9.Task3.solvers;

import edu.hw9.Task3.elements.Cell;

public final class Utils {

    private Utils() {
    }

    public static boolean isValidStep(Cell[][] cells, boolean[][] visited, int row, int col) {
        return ((row >= 0) && (row < cells.length) && (col >= 0) && (col < cells[0].length) && (!visited[row][col]));
    }

    public static boolean isThereErrors(int mazeHeight, int mazeWidth, int startX, int startY, int endX, int endY) {
        if (((startX) > mazeWidth - 1) || (startY > mazeHeight - 1) || (startX < 0) || (startY < 0)) {
            return true;
        }
        return ((endX) > mazeWidth - 1) || (endY > mazeHeight - 1) || (endX < 0) || (endY < 0);
    }
}
