package edu.hw9.Task3.solvers;

import edu.hw9.Task3.elements.Cell;
import edu.hw9.Task3.elements.Coordinate;
import edu.hw9.Task3.elements.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import static edu.hw9.Task3.solvers.Utils.isThereErrors;
import static edu.hw9.Task3.solvers.Utils.isValidStep;

public class ParallelBackTrackingSolver extends RecursiveTask<List<Coordinate>> {
    private final Maze maze;
    private final Coordinate start;
    private final Coordinate end;
    private final List<Coordinate> solvedPath;
    private final boolean[][] visited;

    public ParallelBackTrackingSolver(Maze maze, Coordinate start, Coordinate end) {
        if ((maze == null) || (start == null) || (end == null)) {
            throw new IllegalArgumentException("Maze is null or Coordinates are null");
        }
        if (isThereErrors(maze.height(), maze.width(), start.col(), start.row(), end.col(), end.row())) {
            throw new IllegalArgumentException("Incorrect values in maze or Coordinates");
        }
        this.maze = maze;
        this.start = start;
        this.end = end;
        solvedPath = new ArrayList<>();
        visited = new boolean[maze.height()][maze.width()];
    }

    private ParallelBackTrackingSolver(
        Maze maze,
        Coordinate start,
        Coordinate end,
        List<Coordinate> solvedPath,
        boolean[][] visited
    ) {
        this.maze = maze;
        this.start = start;
        this.end = end;
        this.solvedPath = solvedPath;
        this.visited = visited;
    }

    @Override
    public List<Coordinate> compute() {
        List<Coordinate> result = new ArrayList<>();
        if ((start.row() == end.row()) && (start.col() == end.col())) {
            result.addAll(solvedPath);
            result.add(end);
        }
        solvedPath.add(start);
        visited[start.row()][start.col()] = true;
        Cell[][] cells = maze.grid();
        moveToTheLeft(cells, visited, start, end, solvedPath, result);
        moveToTheRight(cells, visited, start, end, solvedPath, result);
        moveToTheDown(cells, visited, start, end, solvedPath, result);
        moveToTheUp(cells, visited, start, end, solvedPath, result);
        solvedPath.remove(start);
        visited[start.row()][start.col()] = false;
        return result;
    }

    private void moveToTheLeft(
        Cell[][] cells,
        boolean[][] visited,
        Coordinate start,
        Coordinate end,
        List<Coordinate> solvedPath,
        List<Coordinate> result
    ) {
        if ((isValidStep(cells, visited, start.row(), start.col() - 1))
            && (!cells[start.row()][start.col() - 1].isRightWall())) {
            ParallelBackTrackingSolver task = new ParallelBackTrackingSolver(
                maze,
                new Coordinate(start.row(), start.col() - 1),
                end,
                solvedPath,
                visited);
            task.fork();
            result.addAll(task.join());
        }
    }

    private void moveToTheRight(
        Cell[][] cells,
        boolean[][] visited,
        Coordinate start,
        Coordinate end,
        List<Coordinate> solvedPath,
        List<Coordinate> result
    ) {
        if ((isValidStep(cells, visited, start.row(), start.col() + 1))
            && (!cells[start.row()][start.col()].isRightWall())) {
            ParallelBackTrackingSolver task = new ParallelBackTrackingSolver(
                maze,
                new Coordinate(start.row(), start.col() + 1),
                end,
                solvedPath,
                visited);
            task.fork();
            result.addAll(task.join());
        }
    }

    private void moveToTheDown(
        Cell[][] cells,
        boolean[][] visited,
        Coordinate start,
        Coordinate end,
        List<Coordinate> solvedPath,
        List<Coordinate> result
    ) {
        if ((isValidStep(cells, visited, start.row() + 1, start.col()))
            && (!cells[start.row()][start.col()].isDownWall())) {
            ParallelBackTrackingSolver task = new ParallelBackTrackingSolver(
                maze,
                new Coordinate(start.row() + 1, start.col()),
                end,
                solvedPath,
                visited);
            task.fork();
            result.addAll(task.join());
        }
    }

    private void moveToTheUp(
        Cell[][] cells,
        boolean[][] visited,
        Coordinate start,
        Coordinate end,
        List<Coordinate> solvedPath,
        List<Coordinate> result
    ) {
        if ((isValidStep(cells, visited, start.row() - 1, start.col()))
            && (!cells[start.row() - 1][start.col()].isDownWall())) {
            ParallelBackTrackingSolver task = new ParallelBackTrackingSolver(
                maze,
                new Coordinate(start.row() - 1, start.col()),
                end,
                solvedPath,
                visited);
            task.fork();
            result.addAll(task.join());
        }
    }
}
