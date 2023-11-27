package edu.project2.solvers.algorithmLee;

import edu.project2.elements.Cell;
import edu.project2.elements.Coordinate;
import edu.project2.elements.Maze;
import edu.project2.solvers.Solver;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import static edu.project2.solvers.Utils.isThereErrors;
import static edu.project2.solvers.Utils.isValidStep;

public class AlgorithmLeeSolver implements Solver {
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
        int[][] filledArray = fillArray(maze.grid(), visited, start, end);
        getCoordinates(maze.grid(), filledArray, start, end);
        if (solvedPath.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return solvedPath.reversed();
    }

    private void getCoordinates(Cell[][] cells, int[][] filledArray, Coordinate start, Coordinate end) {
        int endX = end.col();
        int endY = end.row();
        int max = filledArray[endY][endX];
        boolean[][] visited = new boolean[cells.length][cells[0].length];
        solvedPath.add(end);
        for (int i = max - 1; i > 0; i--) {
            if (moveToTheLeftFromEnd(cells, visited, endY, endX, i, filledArray)) {
                endX -= 1;
                continue;
            }
            if (moveToTheRightFromEnd(cells, visited, endY, endX, i, filledArray)) {
                endX += 1;
                continue;
            }
            if (moveToTheDownFromEnd(cells, visited, endY, endX, i, filledArray)) {
                endY += 1;
                continue;
            }
            if (moveToTheUpFromEnd(cells, visited, endY, endX, i, filledArray)) {
                endY -= 1;
            }
        }
        solvedPath.add(start);
    }

    private boolean moveToTheLeftFromEnd(
        Cell[][] cells,
        boolean[][] visited,
        int endY,
        int endX,
        int i,
        int[][] filledArray
    ) {
        if ((isValidStep(cells, visited, endY, endX - 1))
            && (!cells[endY][endX - 1].isRightWall()) && (filledArray[endY][endX - 1] == i)) {
            solvedPath.add(new Coordinate(endY, endX - 1));
            return true;
        }
        return false;
    }

    private boolean moveToTheRightFromEnd(
        Cell[][] cells,
        boolean[][] visited,
        int endY,
        int endX,
        int i,
        int[][] filledArray
    ) {
        if ((isValidStep(cells, visited, endY, endX + 1))
            && (!cells[endY][endX].isRightWall()) && (filledArray[endY][endX + 1] == i)) {
            solvedPath.add(new Coordinate(endY, endX + 1));
            return true;
        }
        return false;
    }

    private boolean moveToTheDownFromEnd(
        Cell[][] cells,
        boolean[][] visited,
        int endY,
        int endX,
        int i,
        int[][] filledArray
    ) {
        if ((isValidStep(cells, visited, endY + 1, endX))
            && (!cells[endY][endX].isDownWall()) && (filledArray[endY + 1][endX] == i)) {
            solvedPath.add(new Coordinate(endY + 1, endX));
            return true;
        }
        return false;
    }

    private boolean moveToTheUpFromEnd(
        Cell[][] cells,
        boolean[][] visited,
        int endY,
        int endX,
        int i,
        int[][] filledArray
    ) {
        if ((isValidStep(cells, visited, endY - 1, endX))
            && (!cells[endY - 1][endX].isDownWall()) && (filledArray[endY - 1][endX] == i)) {
            solvedPath.add(new Coordinate(endY - 1, endX));
            return true;
        }
        return false;
    }

    private int[][] fillArray(Cell[][] cells, boolean[][] visited, Coordinate start, Coordinate end) {
        int[][] result = new int[cells.length][cells[0].length];
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(start, 0));
        while (!q.isEmpty()) {
            Node node = q.poll();
            int i = node.coordinate().row();
            int j = node.coordinate().col();
            int distance = node.distance();
            visited[i][j] = true;

            if (i == end.row() && j == end.col()) {
                break;
            }
            q.addAll(getAllNodes(cells, visited, i, j, distance, result));
        }
        return result;
    }

    private List<Node> getAllNodes(Cell[][] cells, boolean[][] visited, int i, int j, int distance, int[][] result) {
        List<Node> list = new ArrayList<>();
        moveToTheLeft(cells, visited, i, j, distance, result, list);
        moveToTheRight(cells, visited, i, j, distance, result, list);
        moveToTheDown(cells, visited, i, j, distance, result, list);
        moveToTheUp(cells, visited, i, j, distance, result, list);
        return list;
    }

    private void moveToTheLeft(
        Cell[][] cells,
        boolean[][] visited,
        int i,
        int j,
        int distance,
        int[][] result,
        List<Node> list
    ) {
        if ((isValidStep(cells, visited, i, j - 1))
            && (!cells[i][j - 1].isRightWall())) {
            list.add(new Node(new Coordinate(i, j - 1), distance + 1));
            result[i][j - 1] = distance + 1;
        }
    }

    private void moveToTheRight(
        Cell[][] cells,
        boolean[][] visited,
        int i,
        int j,
        int distance,
        int[][] result,
        List<Node> list
    ) {
        if ((isValidStep(cells, visited, i, j + 1))
            && (!cells[i][j].isRightWall())) {
            list.add(new Node(new Coordinate(i, j + 1), distance + 1));
            result[i][j + 1] = distance + 1;
        }
    }

    private void moveToTheDown(
        Cell[][] cells,
        boolean[][] visited,
        int i,
        int j,
        int distance,
        int[][] result,
        List<Node> list
    ) {
        if ((isValidStep(cells, visited, i + 1, j))
            && (!cells[i][j].isDownWall())) {
            list.add(new Node(new Coordinate(i + 1, j), distance + 1));
            result[i + 1][j] = distance + 1;
        }
    }

    private void moveToTheUp(
        Cell[][] cells,
        boolean[][] visited,
        int i,
        int j,
        int distance,
        int[][] result,
        List<Node> list
    ) {
        if ((isValidStep(cells, visited, i - 1, j))
            && (!cells[i - 1][j].isDownWall())) {
            list.add(new Node(new Coordinate(i - 1, j), distance + 1));
            result[i - 1][j] = distance + 1;
        }
    }

}
