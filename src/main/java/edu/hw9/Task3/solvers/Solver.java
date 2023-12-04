package edu.hw9.Task3.solvers;

import edu.hw9.Task3.elements.Coordinate;
import edu.hw9.Task3.elements.Maze;
import java.util.List;

public interface Solver {
    List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end);
}
