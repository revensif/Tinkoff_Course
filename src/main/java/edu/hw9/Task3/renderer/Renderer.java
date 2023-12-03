package edu.hw9.Task3.renderer;

import edu.hw9.Task3.elements.Coordinate;
import edu.hw9.Task3.elements.Maze;
import java.util.List;

public interface Renderer {
    String render(Maze maze);

    String render(Maze maze, List<Coordinate> path);
}
