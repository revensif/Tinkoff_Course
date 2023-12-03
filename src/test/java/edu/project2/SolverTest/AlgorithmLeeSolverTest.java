package edu.project2.SolverTest;

import edu.project2.elements.Cell;
import edu.project2.elements.Coordinate;
import edu.project2.elements.Maze;
import edu.project2.solvers.Solver;
import edu.project2.solvers.algorithmLee.AlgorithmLeeSolver;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AlgorithmLeeSolverTest {

    @Test
    @DisplayName("solve() Test : Correct Input")
    public void shouldSolveMaze() {
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
        List<Coordinate> path = solver.solve(maze, new Coordinate(0, 0), new Coordinate(4, 4));
        assertThat(path).isEqualTo(List.of(
            new Coordinate(0, 0),
            new Coordinate(1, 0),
            new Coordinate(2, 0),
            new Coordinate(3, 0),
            new Coordinate(4, 0),
            new Coordinate(4, 1),
            new Coordinate(4, 2),
            new Coordinate(4, 3),
            new Coordinate(4, 4)
        ));
    }

    @Test
    @DisplayName("solve() Test : Incorrect Input")
    public void shouldSolveMazeAndThrowException() {
        Solver solver = new AlgorithmLeeSolver();
        assertThrows(
            IllegalArgumentException.class,
            () -> solver.solve(null, new Coordinate(0, 0), new Coordinate(4, 4))
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> solver.solve(new Maze(10, 10, new Cell[10][10]), null, new Coordinate(4, 4))
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> solver.solve(new Maze(10, 10, new Cell[10][10]), new Coordinate(0, 0), null)
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> solver.solve(new Maze(10, 10, new Cell[10][10]), new Coordinate(-1, 0), new Coordinate(4, 4))
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> solver.solve(new Maze(10, 10, new Cell[10][10]), new Coordinate(-1, 0), new Coordinate(12, 4))
        );
    }
}
