package edu.project2.generator;

import edu.project2.Cell;
import edu.project2.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EllersMazeGenerator implements Generator {
    private int height, width;
    private final Random rand = new Random();
    private static Cell[] cells;
    int[] row_set;
    int set = 1;

    public EllersMazeGenerator() {
    }

    // Генерация лабиринта по методу Эллера
    @Override
    public Maze generate(int height, int width) {
        Cell[][] result = new Cell[height][width];
        this.height = height;
        this.width = width;
        cells = new Cell[width];
        row_set = new int[width];
        for (int i = 0; i < width; i++) {
            cells[i] = new Cell(i, 0);
            row_set[i] = 0;
        }
        for (int i = 0; i < height; i++) {
            result[i] = step(i);
        }
        print(height, width, result);
        return new Maze(height, width, result);
    }

    private Cell[] step(int i) {
        Cell[] previousRow = new Cell[width];
        fillSet(cells);
        mergeSet(cells);
        /* 5.A */
        if (i != height - 1) {
            fillFloor(cells);
            checkHorizontal(cells);
            cells[cells.length - 1].setRightWall(true);
            for (int j = 0; j < width; j++) {
                previousRow[j] =
                    new Cell(cells[j].getX(), cells[j].getY(), cells[j].isRightWall(), cells[j].isDownWall());
            }
            nextRow(cells);
            return previousRow;
        }
        checkEnd(cells);
        for (int j = 0; j < width; j++) {
            previousRow[j] =
                new Cell(cells[j].getX(), cells[j].getY(), cells[j].isRightWall(), cells[j].isDownWall());
        }
        return previousRow;
    }

    /* Шаг 1: Создайте первую строку. Никакие ячейки не будут членами какого-либо набора
    Шаг 2: Присоедините любые ячейки, не входящие в набор, к их собственному уникальному набору */
    private void fillSet(Cell[] row) {
        for (int i = 0; i < row.length; i++) {
            if (row_set[i] == 0) {
                row_set[i] = set++;
            }
        }
    }

    /* Шаг 3: Создавайте правосторонние стены, двигаясь слева направо:
    Случайным образом решайте, добавлять стену или нет (random.nextBoolean) */
    private void mergeSet(Cell[] row) {
        for (int i = 0; i < row.length - 1; i++) {
            Cell current = row[i];
            /* Если текущая ячейка и ячейка справа являются членами одного и того же набора,
            всегда создавайте стену между ними. (Это предотвращает циклы) */
            if ((row_set[i] == row_set[i + 1]) || (rand.nextBoolean())) {
                current.setRightWall(true);
            } else {
                /* Если вы решите не добавлять стену,
                объедините наборы, членами которых являются текущая ячейка и ячейка справа. */
                merge(i, row_set[i]);
            }
        }
    }

    private void merge(int i, int element) {
        int mutableSet = row_set[i + 1];
        for (int j = 0; j < width; j++) {
            if (row_set[j] == mutableSet) {
                row_set[j] = element;
            }
        }
    }

    /* Шаг 4: Создайте нижние стенки, двигаясь слева направо:
    Случайным образом решайте, добавлять стену или нет.
    Убедитесь, что в каждом наборе есть хотя бы одна ячейка без нижней стенки (это предотвращает изоляцию). */
    private void fillFloor(Cell[] row) {
        for (int i = 0; i < row.length; i++) {
            boolean buildBottom = rand.nextBoolean();
            int count = 0;
            for (int j = 0; j < width; j++) {
                if (row_set[i] == row_set[j]) {
                    count++;
                }
            }
            if ((count != 1) && (buildBottom)) {
                row[i].setDownWall(true);
            }
        }
    }

    private void checkHorizontal(Cell[] row) {
        for (int i = 0; i < row.length; i++) {
            if (calculateHorizontalWalls(row_set[i]) == 0) {
                row[i].setDownWall(false);
            }
        }
    }

    private int calculateHorizontalWalls(int element) {
        int countHorizontalWalls = 0;
        for (int i = 0; i < width; i++) {
            if ((row_set[i] == element) && (!cells[i].isDownWall())) {
                countHorizontalWalls++;
            }
        }
        return countHorizontalWalls;
    }

    private void nextRow(Cell[] previousCells) {
        for (int i = 0; i < previousCells.length; i++) {
            previousCells[i].setRightWall(false);
            if (previousCells[i].isDownWall()) {
                row_set[i] = 0;
                previousCells[i].setDownWall(false);
            }
        }
    }

    private void checkEnd(Cell[] row) {
        for (int i = 0; i < row.length - 1; i++) {
            if (row_set[i] != row_set[i + 1]) {
                row[i].setRightWall(false);
                merge(i, row_set[i]);
            }
            row[i].setDownWall(true);
        }
        row[row.length - 1].setDownWall(true);
        row[row.length - 1].setRightWall(true);
    }

    public static void print(int w, int h, Cell[][] result) {
        final StringBuilder builder = new StringBuilder();
        builder.append(" _".repeat(Math.max(0, w)));
        builder.append('\n');
        for (int i = 0; i < h; i++) {
            builder.append('|');
            for (int j = 0; j < w; j++) {
                builder.append(result[i][j].toString());
            }
            builder.append('\n');
        }
        System.out.println(builder);
    }

    public static void main(String[] args) {
        EllersMazeGenerator ellersMazeGenerator = new EllersMazeGenerator();
        Maze maze = ellersMazeGenerator.generate(20, 20);
    }
}
