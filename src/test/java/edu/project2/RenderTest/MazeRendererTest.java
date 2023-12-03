package edu.project2.RenderTest;

import edu.project2.elements.Cell;
import edu.project2.elements.Coordinate;
import edu.project2.elements.Maze;
import edu.project2.renderer.MazeRenderer;
import edu.project2.renderer.Renderer;
import edu.project2.solvers.Solver;
import edu.project2.solvers.algorithmLee.AlgorithmLeeSolver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MazeRendererTest {

    @Test
    @DisplayName("render() Test without solution : Correct Input")
    public void shouldRenderWithoutSolutionMaze() {
        Cell[][] cells = new Cell[5][5];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                cells[i][j] = new Cell(j, i, true, false);
            }
        }
        cells[4] = new Cell[] {
            new Cell(0, 4, false, true),
            new Cell(1, 4, false, true),
            new Cell(2, 4, false, true),
            new Cell(3, 4, false, true),
            new Cell(4, 4, true, true),
        };
        Maze maze = new Maze(5, 5, cells);
        Renderer renderer = new MazeRenderer();
        String actual = renderer.render(maze);
        String expected =
            """
                _____________________
                |   |   |   |   |   |
                |   |   |   |   |   |
                |   |   |   |   |   |
                |   |   |   |   |   |
                |___________________|
                """;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("render() Test without solution : Incorrect Input")
    public void shouldRenderMazeWithoutSolutionAndThrowException() {
        Renderer renderer = new MazeRenderer();
        assertThrows(IllegalArgumentException.class, () -> renderer.render(null));
        Maze maze1 = new Maze(-2, 10, new Cell[2][2]);
        assertThrows(IllegalArgumentException.class, () -> renderer.render(maze1));
        Maze maze2 = new Maze(3, 101, new Cell[2][2]);
        assertThrows(IllegalArgumentException.class, () -> renderer.render(maze2));
    }

    @Test
    @DisplayName("render() Test without solution : Incorrect Input")
    public void shouldRenderMazeWithoutSolutionAndThrowEmptyString() {
        Renderer renderer = new MazeRenderer();
        Maze maze1 = new Maze(3, 10, new Cell[22][9]);
        assertThat(renderer.render(maze1)).isEqualTo("");
        Maze maze2 = new Maze(3, 2, null);
        assertThat(renderer.render(maze2)).isEqualTo("");
    }

    @Test
    @DisplayName("render() Test with solution : Correct Input")
    public void shouldRenderWithSolutionMaze() {
        Cell[][] cells = new Cell[5][5];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                cells[i][j] = new Cell(j, i, true, false);
            }
        }
        cells[4] = new Cell[] {
            new Cell(0, 4, false, true),
            new Cell(1, 4, false, true),
            new Cell(2, 4, false, true),
            new Cell(3, 4, false, true),
            new Cell(4, 4, true, true),
        };
        Maze maze = new Maze(5, 5, cells);
        Solver solver = new AlgorithmLeeSolver();
        List<Coordinate> path = solver.solve(maze, new Coordinate(0, 0), new Coordinate(0, 4));
        Renderer renderer = new MazeRenderer();
        String actual = renderer.render(maze, path);
        String expected =
            """
                _____________________
                | ▪ |   |   |   | ▪ |
                | ▪ |   |   |   | ▪ |
                | ▪ |   |   |   | ▪ |
                | ▪ |   |   |   | ▪ |
                |_▪___▪___▪___▪___▪_|
                """;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("render() Test with solution : Incorrect Input")
    public void shouldRenderMazeWithSolutionAndThrowException() {
        Renderer renderer = new MazeRenderer();
        assertThrows(IllegalArgumentException.class, () -> renderer.render(null, new ArrayList<>()));
        Maze maze1 = new Maze(2, 2, new Cell[2][2]);
        assertThrows(IllegalArgumentException.class, () -> renderer.render(maze1, null));
        assertThrows(IllegalArgumentException.class, () -> renderer.render(null, null));
        List<Coordinate> list = new ArrayList<>();
        list.add(new Coordinate(0, 0));
        Maze maze2 = new Maze(-2, 10, new Cell[2][2]);
        assertThrows(IllegalArgumentException.class, () -> renderer.render(maze2, list));
        Maze maze3 = new Maze(3, 101, new Cell[2][2]);
        assertThrows(IllegalArgumentException.class, () -> renderer.render(maze3, list));
    }

    @Test
    @DisplayName("render() Test with solution : Incorrect Input")
    public void shouldRenderMazeWithSolutionAndThrowEmptyString() {
        Renderer renderer = new MazeRenderer();
        List<Coordinate> list = new ArrayList<>();
        list.add(new Coordinate(0, 0));
        Maze maze1 = new Maze(3, 10, new Cell[22][9]);
        assertThat(renderer.render(maze1, list)).isEqualTo("");
        Maze maze2 = new Maze(3, 2, null);
        assertThat(renderer.render(maze2, list)).isEqualTo("");
    }
}
