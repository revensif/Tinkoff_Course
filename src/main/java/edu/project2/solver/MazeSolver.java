package edu.project2.solver;

import edu.project2.elements.Cell;
import edu.project2.elements.Coordinate;
import edu.project2.elements.Maze;
import java.util.ArrayList;
import java.util.List;

public class MazeSolver implements Solver {
    List<Coordinate> solvedPath = new ArrayList<>();

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        if ((maze == null) || (start == null) || (end == null)) {
            throw new IllegalArgumentException();
        }
        if (isThereErrors(maze.getHeight(), maze.getWidth(), start.col(), start.row(), end.col(), end.row())) {
            throw new IllegalArgumentException();
        }
        boolean[][] visited = new boolean[maze.getHeight()][maze.getWidth()];
        breadthFirstSearch(maze.getGrid(), visited, start, end, new ArrayList<>());
        return solvedPath;
    }

    private boolean isThereErrors(int mazeHeight, int mazeWidth, int startX, int startY, int endX, int endY) {
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

    private boolean isValidStep(Cell[][] cells, boolean[][] visited, int row, int col) {
        return ((row >= 0) && (row < cells.length) && (col >= 0) && (col < cells[0].length) && (!visited[row][col]));
    }
}
