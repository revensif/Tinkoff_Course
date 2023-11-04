package edu.project2.solvers.algorithmLee;

import edu.project2.elements.Cell;
import edu.project2.elements.Coordinate;
import edu.project2.elements.Maze;
import edu.project2.solvers.Solver;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import static edu.project2.solvers.BackTrackingSolver.isThereErrors;
import static edu.project2.solvers.BackTrackingSolver.isValidStep;

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
            //Движение влево
            if ((isValidStep(cells, visited, endY, endX - 1))
                && (!cells[endY][endX - 1].isRightWall()) && (filledArray[endY][endX - 1] == i)) {
                endX -= 1;
                solvedPath.add(new Coordinate(endY, endX));
                continue;
            }
            //Движение вправо
            if ((isValidStep(cells, visited, endY, endX + 1))
                && (!cells[endY][endX].isRightWall()) && (filledArray[endY][endX + 1] == i)) {
                endX += 1;
                solvedPath.add(new Coordinate(endY, endX));
                continue;
            }
            //Движение вниз
            if ((isValidStep(cells, visited, endY + 1, endX))
                && (!cells[endY][endX].isDownWall()) && (filledArray[endY + 1][endX] == i)) {
                endY += 1;
                solvedPath.add(new Coordinate(endY, endX));
                continue;
            }
            //Движение вверх
            if ((isValidStep(cells, visited, endY - 1, endX))
                && (!cells[endY - 1][endX].isDownWall()) && (filledArray[endY - 1][endX] == i)) {
                endY -= 1;
                solvedPath.add(new Coordinate(endY, endX));
            }
        }
        solvedPath.add(start);
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
        //Движение влево
        if ((isValidStep(cells, visited, i, j - 1))
            && (!cells[i][j - 1].isRightWall())) {
            list.add(new Node(new Coordinate(i, j - 1), distance + 1));
            result[i][j - 1] = distance + 1;
        }
        //Движение вправо
        if ((isValidStep(cells, visited, i, j + 1))
            && (!cells[i][j].isRightWall())) {
            list.add(new Node(new Coordinate(i, j + 1), distance + 1));
            result[i][j + 1] = distance + 1;
        }
        //Движение вниз
        if ((isValidStep(cells, visited, i + 1, j))
            && (!cells[i][j].isDownWall())) {
            list.add(new Node(new Coordinate(i + 1, j), distance + 1));
            result[i + 1][j] = distance + 1;
        }
        //Движение вверх
        if ((isValidStep(cells, visited, i - 1, j))
            && (!cells[i - 1][j].isDownWall())) {
            list.add(new Node(new Coordinate(i - 1, j), distance + 1));
            result[i - 1][j] = distance + 1;
        }
        return list;
    }

}
