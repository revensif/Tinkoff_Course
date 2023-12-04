package edu.hw9.Task3.generators;

import edu.hw9.Task3.elements.Maze;

public interface Generator {
    Maze generate(int height, int width);
}
