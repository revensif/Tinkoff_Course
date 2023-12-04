package edu.hw9;

import edu.hw9.Task3.elements.Coordinate;
import edu.hw9.Task3.elements.Maze;
import edu.hw9.Task3.generators.EllersMazeGenerator;
import edu.hw9.Task3.generators.Generator;
import edu.hw9.Task3.renderer.MazeRenderer;
import edu.hw9.Task3.renderer.Renderer;
import edu.hw9.Task3.solvers.BackTrackingSolver;
import edu.hw9.Task3.solvers.ParallelBackTrackingSolver;
import edu.hw9.Task3.solvers.Solver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Random;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {
    private static final int HEIGHT = 10;
    private static final int WIDTH = 10;

    @Test
    @DisplayName("ParallelBackTrackingSolver Test")
    void shouldSolveMaze() {
        Generator generator = new EllersMazeGenerator(new Random());
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(HEIGHT - 1, WIDTH - 1);
        Maze maze = generator.generate(HEIGHT, WIDTH);
        Solver simpleSolver = new BackTrackingSolver();
        ParallelBackTrackingSolver parallelSolver = new ParallelBackTrackingSolver(maze, start, end);
        List<Coordinate> simpleList = simpleSolver.solve(maze, start, end);
        List<Coordinate> parallelList = parallelSolver.compute();
        assertThat(simpleList).isEqualTo(parallelList);
        Renderer renderer = new MazeRenderer();
        String simpleMaze = renderer.render(maze, simpleList);
        String parallelMaze = renderer.render(maze, parallelList);
        assertThat(simpleMaze).isEqualTo(parallelMaze);
    }
}
