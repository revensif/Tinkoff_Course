package edu.project2.solvers;

import edu.project2.elements.Cell;
import edu.project2.elements.Coordinate;
import edu.project2.elements.Maze;
import java.util.ArrayList;
import java.util.List;

public class BackTrackingSolver implements Solver {
    List<Coordinate> solvedPath;

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        solvedPath = new ArrayList<>();
        if ((maze == null) || (start == null) || (end == null)) {
            throw new IllegalArgumentException("Maze is null or Coordinates are null");
        }
        if (isThereErrors(maze.height(), maze.width(), start.col(), start.row(), end.col(), end.row())) {
            throw new IllegalArgumentException("Incorrect values in maze or Coordinates");
        }
        boolean[][] visited = new boolean[maze.height()][maze.width()];
        breadthFirstSearch(maze.grid(), visited, start, end, new ArrayList<>());
        if (solvedPath.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return solvedPath;
    }

    public static boolean isThereErrors(int mazeHeight, int mazeWidth, int startX, int startY, int endX, int endY) {
        if (((startX) > mazeWidth - 1) || (startY > mazeHeight - 1) || (startX < 0) || (startY < 0)) {
            return true;
        }
        return ((endX) > mazeWidth - 1) || (endY > mazeHeight - 1) || (endX < 0) || (endY < 0);
    }

    //Алгоритм BackTracking или поиск в глубину
    private List<Coordinate> breadthFirstSearch(
        Cell[][] cells,
        boolean[][] visited,
        Coordinate start,
        Coordinate end,
        List<Coordinate> list
    ) {
        if ((start.row() == end.row()) && (start.col() == end.col())) {
            solvedPath.addAll(list);
            solvedPath.add(end);
            return list;
        }
        List<Coordinate> newList = list;
        visited[start.row()][start.col()] = true;
        list.add(start);
        //Движение влево
        if ((isValidStep(cells, visited, start.row(), start.col() - 1))
            && (!cells[start.row()][start.col() - 1].isRightWall())) {
            newList = breadthFirstSearch(cells, visited, new Coordinate(start.row(), start.col() - 1), end, newList);
        }
        //Движение вправо
        if ((isValidStep(cells, visited, start.row(), start.col() + 1))
            && (!cells[start.row()][start.col()].isRightWall())) {
            newList = breadthFirstSearch(cells, visited, new Coordinate(start.row(), start.col() + 1), end, newList);
        }
        //Движение вниз
        if ((isValidStep(cells, visited, start.row() + 1, start.col()))
            && (!cells[start.row()][start.col()].isDownWall())) {
            newList = breadthFirstSearch(cells, visited, new Coordinate(start.row() + 1, start.col()), end, newList);
        }
        //Движение вверх
        if ((isValidStep(cells, visited, start.row() - 1, start.col()))
            && (!cells[start.row() - 1][start.col()].isDownWall())) {
            newList = breadthFirstSearch(cells, visited, new Coordinate(start.row() - 1, start.col()), end, newList);
        }
        newList.remove(start);
        visited[start.row()][start.col()] = false;
        return newList;
    }

    public static boolean isValidStep(Cell[][] cells, boolean[][] visited, int row, int col) {
        return ((row >= 0) && (row < cells.length) && (col >= 0) && (col < cells[0].length) && (!visited[row][col]));
    }
}
