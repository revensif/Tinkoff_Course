package edu.project4;

import edu.project4.Renderers.MultipleThreadRenderer;
import edu.project4.Renderers.Renderer;
import edu.project4.Renderers.SingleThreadRenderer;
import edu.project4.Transformations.DiskTransformation;
import edu.project4.Transformations.HeartTransformation;
import edu.project4.Transformations.Transformation;
import edu.project4.Utils.FractalImage;
import edu.project4.Utils.Pixel;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.project4.Utils.ImageUtils.HEIGHT;
import static edu.project4.Utils.ImageUtils.WIDTH;
import static org.assertj.core.api.Assertions.assertThat;

public class RenderersTest {
    private static final int THREADS = 4;
    private static final int SAMPLES = 20;
    private static final int ITER_PER_SAMPLE = 1000;
    private static final int SYMMETRY = 2;
    private static final List<Transformation> VARIATIONS = List.of(new HeartTransformation(), new DiskTransformation());

    @Test
    @DisplayName("SingleThreadRenderer Test")
    void shouldRenderImageWithSingleThread() {
        FractalImage fractalImage = FractalImage.create(WIDTH, HEIGHT);
        Renderer renderer = new SingleThreadRenderer();

        FractalImage newImage = renderer.render(fractalImage, VARIATIONS, SAMPLES, ITER_PER_SAMPLE, SYMMETRY);
        assertThat(fractalImage.data().length).isEqualTo(newImage.data().length);
        assertThat(calculatePixels(newImage)).isTrue();
        assertThat(calculatePixels(fractalImage)).isFalse();
    }

    @Test
    @DisplayName("MultipleThreadRenderer Test")
    void shouldRenderImageWithMultipleThread() {
        FractalImage fractalImage = FractalImage.create(WIDTH, HEIGHT);
        Renderer renderer = new MultipleThreadRenderer(THREADS);
        FractalImage newImage = renderer.render(fractalImage, VARIATIONS, SAMPLES, ITER_PER_SAMPLE, SYMMETRY);
        assertThat(fractalImage.data().length).isEqualTo(newImage.data().length);
        assertThat(calculatePixels(newImage)).isTrue();
        assertThat(calculatePixels(fractalImage)).isFalse();
    }

    private boolean calculatePixels(FractalImage fractalImage) {
        for (Pixel[] pixels : fractalImage.data()) {
            for (Pixel pixel : pixels) {
                if ((pixel.getR() != 0) || (pixel.getG() != 0) || (pixel.getB() != 0)) {
                    return true;
                }
            }
        }
        return false;
    }
}
