package edu.hw9.Task3.renderer;

import edu.hw9.Task3.elements.Cell;
import edu.hw9.Task3.elements.Coordinate;
import edu.hw9.Task3.elements.Maze;
import java.util.Arrays;
import java.util.List;
import static edu.hw9.Task3.generators.EllersMazeGenerator.MAX_SIZE;

public class MazeRenderer implements Renderer {
    private static final String MAZE_IS_NULL = "Maze is null";
    private static final String WRONG_SIZE = "The size of the maze should be in the range [1:100]";
    private static final String FLOOR = "_";
    private static final String PATH = "▪";
    private static final int FOUR = 4;

    @Override
    public String render(Maze maze) {
        if (maze == null) {
            throw new IllegalArgumentException(MAZE_IS_NULL);
        }
        if ((maze.height() < 1) || (maze.width() < 1) || (maze.height() > MAX_SIZE) || (maze.width() > MAX_SIZE)) {
            throw new IllegalArgumentException(WRONG_SIZE);
        }
        if (maze.grid() == null) {
            return "";
        }
        List<Cell> list = Arrays.stream(maze.grid()).flatMap(Arrays::stream).toList();
        if ((list.isEmpty()) || (list.contains(null)) || (list.size() != maze.width() * maze.height())) {
            return "";
        }
        Cell[][] cells = maze.grid();
        return FLOOR.repeat(maze.width() * FOUR + 1) + '\n' + getAllMazeWithoutSolution(maze, cells);
    }

    private String getAllMazeWithoutSolution(Maze maze, Cell[][] cells) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < maze.height(); i++) {
            builder.append('|');
            builder.append(appendEntireRow(maze, cells, i));
            builder.append('\n');
        }
        return String.valueOf(builder);
    }

    private String appendEntireRow(Maze maze, Cell[][] cells, int i) {
        StringBuilder builder = new StringBuilder();
        for (int j = 0; j < maze.width(); j++) {
            builder.append(getCurrentCell(cells, i, j, FLOOR, " "));
        }
        return String.valueOf(builder);
    }

    private String getCurrentCell(Cell[][] cells, int i, int j, String s1, String s2) {
        StringBuilder builder = new StringBuilder();
        if ((cells[i][j].isDownWall()) && (cells[i][j].isRightWall())) {
            builder.append(FLOOR).append(s1).append("_|");
        } else if (cells[i][j].isDownWall()) {
            builder.append(FLOOR).append(s1).append("__");
        } else if (cells[i][j].isRightWall()) {
            builder.append(" ").append(s2).append(" |");
        } else {
            builder.append(" ").append(s2).append("  ");
        }
        return String.valueOf(builder);
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        if ((maze == null) || (path == null) || (path.isEmpty())) {
            throw new IllegalArgumentException("Maze is null or Path is null or empty");
        }
        if ((maze.height() < 1) || (maze.width() < 1) || (maze.height() > MAX_SIZE) || (maze.width() > MAX_SIZE)) {
            throw new IllegalArgumentException(WRONG_SIZE);
        }
        if (maze.grid() == null) {
            return "";
        }
        List<Cell> list = Arrays.stream(maze.grid()).flatMap(Arrays::stream).toList();
        if ((list.isEmpty()) || (list.contains(null)) || (list.size() != maze.width() * maze.height())) {
            return "";
        }
        Cell[][] cells = maze.grid();
        return FLOOR.repeat(maze.width() * FOUR + 1) + '\n' + getAllMazeWithSolution(maze, cells, path);
    }

    private String getAllMazeWithSolution(Maze maze, Cell[][] cells, List<Coordinate> path) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < maze.height(); i++) {
            builder.append('|');
            builder.append(appendEntireRowWithCells(maze, cells, i, path));
            builder.append('\n');
        }
        return String.valueOf(builder);
    }

    private String appendEntireRowWithCells(Maze maze, Cell[][] cells, int i, List<Coordinate> path) {
        StringBuilder builder = new StringBuilder();
        for (int j = 0; j < maze.width(); j++) {
            Coordinate coordinate = getCoordinate(path, i, j);
            if (coordinate == null) {
                builder.append(getCurrentCell(cells, i, j, FLOOR, " "));
            } else {
                builder.append(getCurrentCell(cells, i, j, PATH, PATH));
            }

        }
        return String.valueOf(builder);
    }

    private Coordinate getCoordinate(List<Coordinate> path, int i, int j) {
        for (Coordinate coordinate : path) {
            if ((coordinate.row() == i) && (coordinate.col() == j)) {
                return coordinate;
            }
        }
        return null;
    }
}
