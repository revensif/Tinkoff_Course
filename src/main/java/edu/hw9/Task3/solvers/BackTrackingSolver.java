package edu.hw9.Task3.solvers;

import edu.hw9.Task3.elements.Cell;
import edu.hw9.Task3.elements.Coordinate;
import edu.hw9.Task3.elements.Maze;
import java.util.ArrayList;
import java.util.List;
import static edu.hw9.Task3.solvers.Utils.isThereErrors;
import static edu.hw9.Task3.solvers.Utils.isValidStep;

public class BackTrackingSolver implements Solver {
    private List<Coordinate> solvedPath;

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

    //Алгоритм BackTracking или поиск в ширину
    private void breadthFirstSearch(
        Cell[][] cells,
        boolean[][] visited,
        Coordinate start,
        Coordinate end,
        List<Coordinate> list
    ) {
        if ((start.row() == end.row()) && (start.col() == end.col())) {
            solvedPath.addAll(list);
            solvedPath.add(end);
            return;
        }
        visited[start.row()][start.col()] = true;
        list.add(start);
        moveToTheLeft(cells, visited, start, end, list);
        moveToTheRight(cells, visited, start, end, list);
        moveToTheDown(cells, visited, start, end, list);
        moveToTheUp(cells, visited, start, end, list);
        list.remove(start);
        visited[start.row()][start.col()] = false;
    }

    private void moveToTheLeft(
        Cell[][] cells,
        boolean[][] visited,
        Coordinate start,
        Coordinate end,
        List<Coordinate> newList
    ) {
        if ((isValidStep(cells, visited, start.row(), start.col() - 1))
            && (!cells[start.row()][start.col() - 1].isRightWall())) {
            breadthFirstSearch(cells, visited, new Coordinate(start.row(), start.col() - 1), end, newList);
        }
    }

    private void moveToTheRight(
        Cell[][] cells,
        boolean[][] visited,
        Coordinate start,
        Coordinate end,
        List<Coordinate> newList
    ) {
        if ((isValidStep(cells, visited, start.row(), start.col() + 1))
            && (!cells[start.row()][start.col()].isRightWall())) {
            breadthFirstSearch(cells, visited, new Coordinate(start.row(), start.col() + 1), end, newList);
        }
    }

    private void moveToTheDown(
        Cell[][] cells,
        boolean[][] visited,
        Coordinate start,
        Coordinate end,
        List<Coordinate> newList
    ) {
        if ((isValidStep(cells, visited, start.row() + 1, start.col()))
            && (!cells[start.row()][start.col()].isDownWall())) {
            breadthFirstSearch(cells, visited, new Coordinate(start.row() + 1, start.col()), end, newList);
        }
    }

    private void moveToTheUp(
        Cell[][] cells,
        boolean[][] visited,
        Coordinate start,
        Coordinate end,
        List<Coordinate> newList
    ) {
        if ((isValidStep(cells, visited, start.row() - 1, start.col()))
            && (!cells[start.row() - 1][start.col()].isDownWall())) {
            breadthFirstSearch(cells, visited, new Coordinate(start.row() - 1, start.col()), end, newList);
        }
    }
}
