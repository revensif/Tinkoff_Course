package edu.project2.elements;

public final class Maze {
    private static final int DEFAULT_VALUE = 10;

    private final int height;
    private final int width;
    private final Cell[][] grid;

    public Maze() {
        height = DEFAULT_VALUE;
        width = DEFAULT_VALUE;
        grid = new Cell[height][width];
    }

    public Maze(int height, int width, Cell[][] grid) {
        this.height = height;
        this.width = width;
        this.grid = grid;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
