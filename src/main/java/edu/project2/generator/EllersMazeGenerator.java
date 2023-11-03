package edu.project2.generator;

import edu.project2.elements.Cell;
import edu.project2.elements.Maze;
import java.util.Random;

public class EllersMazeGenerator implements Generator {
    private int height;
    private int width;
    private final Random rand = new Random();
    private static Cell[] cells;
    int[] rowSet;
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
        rowSet = new int[width];
        //Шаг 1
        for (int i = 0; i < width; i++) {
            cells[i] = new Cell(i, 0);
            rowSet[i] = 0;
        }
        //Шаг 2-5
        for (int i = 0; i < height; i++) {
            result[i] = step(i);
        }
        return new Maze(height, width, result);
    }

    private Cell[] step(int i) {
        Cell[] previousRow = new Cell[width];
        //Шаг 2:
        fillSet(cells);
        //Шаг 3:
        mergeSet(cells);
        if (i != height - 1) {
            //Шаг 4:
            fillFloor(cells);
            checkHorizontal(cells);
            cells[cells.length - 1].setRightWall(true);
            previousRow = copy(cells);
            //Шаг 5А:
            nextRow(cells);
            return previousRow;
        }
        //Шаг 5Б:
        checkEnd(cells);
        previousRow = copy(cells);
        return previousRow;
    }

    private Cell[] copy(Cell[] cells) {
        Cell[] copyCells = new Cell[cells.length];
        for (int j = 0; j < width; j++) {
            copyCells[j] =
                new Cell(cells[j].getX(), cells[j].getY(), cells[j].isRightWall(), cells[j].isDownWall());
        }
        return copyCells;
    }

    /* Шаг 1: Создайте первую строку. Никакие ячейки не будут членами какого-либо набора
    Шаг 2: Присоедините любые ячейки, не входящие в набор, к их собственному уникальному набору */
    private void fillSet(Cell[] row) {
        for (int i = 0; i < row.length; i++) {
            if (rowSet[i] == 0) {
                rowSet[i] = set++;
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
            if ((rowSet[i] == rowSet[i + 1]) || (rand.nextBoolean())) {
                current.setRightWall(true);
            } else {
                /* Если вы решите не добавлять стену,
                объедините наборы, членами которых являются текущая ячейка и ячейка справа. */
                merge(i, rowSet[i]);
            }
        }
    }

    private void merge(int i, int element) {
        int mutableSet = rowSet[i + 1];
        for (int j = 0; j < width; j++) {
            if (rowSet[j] == mutableSet) {
                rowSet[j] = element;
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
                if (rowSet[i] == rowSet[j]) {
                    count++;
                }
            }
            if ((count != 1) && (buildBottom)) {
                row[i].setDownWall(true);
            }
        }
    }

    //Продолжение Шага 4
    private void checkHorizontal(Cell[] row) {
        for (int i = 0; i < row.length; i++) {
            if (calculateHorizontalWalls(rowSet[i]) == 0) {
                row[i].setDownWall(false);
            }
        }
    }

    private int calculateHorizontalWalls(int element) {
        int countHorizontalWalls = 0;
        for (int i = 0; i < width; i++) {
            if ((rowSet[i] == element) && (!cells[i].isDownWall())) {
                countHorizontalWalls++;
            }
        }
        return countHorizontalWalls;
    }

    // Шаг 5А: Если вы решите добавить еще одну строку:
    private void nextRow(Cell[] previousCells) {
        for (int i = 0; i < previousCells.length; i++) {
            previousCells[i].setY(previousCells[i].getY() + 1);
            //Удалите все правые стены
            previousCells[i].setRightWall(false);
            //Удалить все нижние стенки
            if (previousCells[i].isDownWall()) {
                //Удалите ячейки с нижней стенкой из их набора
                rowSet[i] = 0;
                previousCells[i].setDownWall(false);
            }
        }
    }

    /* Шаг 5Б: Если вы решите пройти лабиринт
     Перемещение слева направо: */
    private void checkEnd(Cell[] row) {
        for (int i = 0; i < row.length - 1; i++) {
            // Если текущая ячейка и ячейка справа являются членами другого набора:
            if (rowSet[i] != rowSet[i + 1]) {
                //Удалите правую стену
                row[i].setRightWall(false);
                //Объедините множества, членами которых являются текущая ячейка и ячейка справа.
                merge(i, rowSet[i]);
            }
            //Добавьте нижнюю стенку к каждой ячейке
            row[i].setDownWall(true);
        }
        row[row.length - 1].setDownWall(true);
        row[row.length - 1].setRightWall(true);
    }
}
