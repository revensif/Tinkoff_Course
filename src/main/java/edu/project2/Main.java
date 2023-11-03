package edu.project2;

import edu.project2.elements.Coordinate;
import edu.project2.elements.Maze;
import edu.project2.generator.EllersMazeGenerator;
import edu.project2.generator.Generator;
import edu.project2.renderer.MazeRenderer;
import edu.project2.renderer.Renderer;
import edu.project2.solver.MazeSolver;
import edu.project2.solver.Solver;
import java.util.List;

public final class Main {
    private static final int HEIGHT = 5;
    private static final int WIDTH = 5;

    private Main() {
    }

    public static void main(String[] args) {
        Generator generator = new EllersMazeGenerator();
        Maze maze = generator.generate(HEIGHT, WIDTH);
        Solver solver = new MazeSolver();
        List<Coordinate> list = solver.solve(maze, new Coordinate(0, 0), new Coordinate(HEIGHT - 1, WIDTH - 1));
        Renderer renderer = new MazeRenderer();
        System.out.println(renderer.render(maze));
        System.out.println(renderer.render(maze, list));
    }
}
