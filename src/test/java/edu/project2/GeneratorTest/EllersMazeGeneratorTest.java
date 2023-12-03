package edu.project2.GeneratorTest;

import edu.project2.elements.Cell;
import edu.project2.elements.Maze;
import edu.project2.generators.EllersMazeGenerator;
import edu.project2.generators.Generator;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class EllersMazeGeneratorTest {

    @Test
    @DisplayName("generate() Test : Correct Input")
    public void shouldGenerateMaze() {
        Random rand = mock(Random.class);
        Mockito.when(rand.nextBoolean()).thenReturn(true);
        Generator generator = new EllersMazeGenerator(rand);
        Maze maze = generator.generate(5, 5);
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
        Maze maze2 = new Maze(5, 5, cells);
        assertThat(maze.grid()).isEqualTo(maze2.grid());
    }

    @Test
    @DisplayName("generate() Test : Incorrect Input")
    public void shouldGenerateMazeAndThrowException() {
        Generator generator = new EllersMazeGenerator(new Random());
        assertThrows(IllegalArgumentException.class, () -> generator.generate(-1, -3));
        assertThrows(IllegalArgumentException.class, () -> generator.generate(102, 20));
    }
}
