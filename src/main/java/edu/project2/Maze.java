package edu.project2;

import java.util.Arrays;

public final class Maze {

    private final int height;
    private final int width;
    private final Cell[][] grid;

    public Maze() {
        height = 10;
        width = 10;
        grid = new Cell[height][width];
    }

    public Maze(int height, int width, Cell[][] grid) {
        this.height = height;
        this.width = width;
        this.grid = grid;
    }

    public static void main(String[] args) {
        String s = "\uD83D\uDF94";
        System.out.println("⏹⏹⏹⏹⏹⏹⏹⏹");
        System.out.println("⏹◻⏹⏹⏹◻◻⏹");
        System.out.println("⏹◻◻▣▣▣⏹⏹");
        System.out.println("⏹▣▣▣⏹▣⏹⏹");
        System.out.println("⏹◻⏹⏹◻▣◻⏹");
        System.out.println("⏹◻⏹⏹⏹▣⏹⏹");
        System.out.println("⏹◻⏹◻◻▣◻⏹");
        System.out.println("⏹⏹⏹⏹⏹⏹⏹⏹");
    }
}
