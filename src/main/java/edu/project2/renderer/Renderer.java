package edu.project2.renderer;

import edu.project2.elements.Coordinate;
import edu.project2.elements.Maze;
import java.util.List;

public interface Renderer {
    String render(Maze maze);

    String render(Maze maze, List<Coordinate> path);
}
