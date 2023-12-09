package edu.project4.ImageUtilsTest;

import edu.project4.Correction.GammaCorrection;
import edu.project4.Correction.ImageProcessor;
import edu.project4.Renderers.Renderer;
import edu.project4.Renderers.SingleThreadRenderer;
import edu.project4.Transformations.DiskTransformation;
import edu.project4.Transformations.SphericalTransformation;
import edu.project4.Transformations.Transformation;
import edu.project4.Utils.FractalImage;
import edu.project4.Utils.ImageFormat;
import edu.project4.Utils.ImageUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.project4.Utils.ImageUtils.HEIGHT;
import static edu.project4.Utils.ImageUtils.WIDTH;
import static org.assertj.core.api.Assertions.assertThat;

public class ImageUtilsTest {
    private static final Path PATH = Paths.get("src/test/java/edu/project4/ImageUtilsTest/test.bmp");
    private static final int SAMPLES = 20;
    private static final int ITER_PER_SAMPLES = 1_000_000;
    private static final int SYMMETRY = 3;
    private static final double GAMMA = 2.2;

    @Test
    @DisplayName("ImageUtils Test")
    void shouldSaveImage() throws IOException {
        if (Files.exists(PATH)) {
            Files.delete(PATH);
        }
        FractalImage image = FractalImage.create(WIDTH, HEIGHT);
        Renderer renderer = new SingleThreadRenderer();
        List<Transformation> list = List.of(new SphericalTransformation(), new DiskTransformation());
        image = renderer.render(image, list, SAMPLES, ITER_PER_SAMPLES, SYMMETRY);
        ImageProcessor imageProcessor = new GammaCorrection(GAMMA);
        imageProcessor.process(image);
        assertThat(Files.exists(PATH)).isFalse();
        ImageUtils.save(image, PATH, ImageFormat.BMP);
        assertThat(Files.exists(PATH)).isTrue();
    }
}
