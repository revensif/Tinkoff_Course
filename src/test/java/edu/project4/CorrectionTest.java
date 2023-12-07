package edu.project4;

import edu.project4.Correction.GammaCorrection;
import edu.project4.Correction.ImageProcessor;
import edu.project4.Utils.FractalImage;
import edu.project4.Utils.Pixel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.project4.Utils.ImageUtils.HEIGHT;
import static edu.project4.Utils.ImageUtils.WIDTH;
import static org.assertj.core.api.Assertions.assertThat;

public class CorrectionTest {

    @Test
    @DisplayName("GammaCorrection Test")
    void shouldCorrectGamma() {
        Pixel[][] pixelsBefore = createPixelsWithColors();
        Pixel[][] pixelsAfter = createPixelsWithColors();
        FractalImage afterCorrection = new FractalImage(pixelsAfter, WIDTH, HEIGHT);
        ImageProcessor processor = new GammaCorrection(2.2);
        processor.process(afterCorrection);
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                assertThat(pixelsBefore[i][j].getR() >= pixelsAfter[i][j].getR()).isTrue();
                assertThat(pixelsBefore[i][j].getG() >= pixelsAfter[i][j].getG()).isTrue();
                assertThat(pixelsBefore[i][j].getB() >= pixelsAfter[i][j].getB()).isTrue();
            }
        }
    }

    private Pixel[][] createPixelsWithColors() {
        Pixel[][] pixels = new Pixel[WIDTH][HEIGHT];
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                pixels[i][j] = new Pixel(50, 50, 50, 1, 0);
            }
        }
        return pixels;
    }
}
