package edu.hw9.Task3;

import edu.hw9.Task3.elements.Coordinate;
import edu.hw9.Task3.elements.Maze;
import edu.hw9.Task3.generators.EllersMazeGenerator;
import edu.hw9.Task3.generators.Generator;
import edu.hw9.Task3.renderer.MazeRenderer;
import edu.hw9.Task3.renderer.Renderer;
import edu.hw9.Task3.solvers.BackTrackingSolver;
import edu.hw9.Task3.solvers.ParallelBackTrackingSolver;
import edu.hw9.Task3.solvers.Solver;
import java.util.List;
import java.util.Random;

public final class Main {
    private static final int HEIGHT = 100;
    private static final int WIDTH = 100;

    private Main() {
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public static void main(String[] args) {
        Generator generator = new EllersMazeGenerator(new Random());
        Maze maze = generator.generate(HEIGHT, WIDTH);
        Solver solver1 = new BackTrackingSolver();
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(HEIGHT - 1, WIDTH - 1);
        ParallelBackTrackingSolver solver2 = new ParallelBackTrackingSolver(maze, start, end);
        List<Coordinate> list1 = solver1.solve(maze, start, end);
        Renderer renderer = new MazeRenderer();
        System.out.println(renderer.render(maze));
        System.out.println(renderer.render(maze, list1));
        List<Coordinate> list2 = solver2.compute();
        System.out.println(renderer.render(maze, list2));
    }
}
