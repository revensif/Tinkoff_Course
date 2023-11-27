package edu.project2;

import edu.project2.elements.Coordinate;
import edu.project2.elements.Maze;
import edu.project2.generators.EllersMazeGenerator;
import edu.project2.generators.Generator;
import edu.project2.renderer.MazeRenderer;
import edu.project2.renderer.Renderer;
import edu.project2.solvers.BackTrackingSolver;
import edu.project2.solvers.Solver;
import edu.project2.solvers.algorithmLee.AlgorithmLeeSolver;
import java.util.List;
import java.util.Random;

public final class Main {
    private static final int HEIGHT = 10;
    private static final int WIDTH = 10;

    private Main() {
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public static void main(String[] args) {
        Generator generator = new EllersMazeGenerator(new Random());
        Maze maze = generator.generate(HEIGHT, WIDTH);
        Solver solver1 = new BackTrackingSolver();
        Solver solver2 = new AlgorithmLeeSolver();
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(HEIGHT - 1, WIDTH - 1);
        List<Coordinate> list1 = solver1.solve(maze, start, end);
        Renderer renderer = new MazeRenderer();
        System.out.println(renderer.render(maze));
        System.out.println(renderer.render(maze, list1));
        List<Coordinate> list2 = solver2.solve(maze, start, end);
        System.out.println(renderer.render(maze, list2));
    }
}
