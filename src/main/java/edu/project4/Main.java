package edu.project4;

import edu.project4.Correction.GammaCorrection;
import edu.project4.Correction.ImageProcessor;
import edu.project4.Renderers.MultipleThreadRenderer;
import edu.project4.Renderers.Renderer;
import edu.project4.Transformations.SphericalTransformation;
import edu.project4.Transformations.Transformation;
import edu.project4.Utils.FractalImage;
import edu.project4.Utils.ImageFormat;
import edu.project4.Utils.ImageUtils;
import edu.project4.Utils.TimeCalculation;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import static edu.project4.Utils.ImageUtils.HEIGHT;
import static edu.project4.Utils.ImageUtils.WIDTH;

public final class Main {
    private static final Path PATH = Paths.get("src/main/java/edu/project4/Flames");
    private static final int SAMPLES = 20;
    private static final int ITER_PER_SAMPLES = 1_000_000;
    private static final int SYMMETRY = 3;
    private static final double GAMMA = 2.2;

    private Main() {
    }

    @SuppressWarnings("MagicNumber")
    public static void main(String[] args) {
        FractalImage image = FractalImage.create(WIDTH, HEIGHT);
        Renderer renderer = new MultipleThreadRenderer(4);
        List<Transformation> list = List.of(new SphericalTransformation());
        image = renderer.render(image, list, SAMPLES, ITER_PER_SAMPLES, SYMMETRY);
        ImageProcessor imageProcessor = new GammaCorrection(GAMMA);
        imageProcessor.process(image);
        ImageUtils.save(image, Paths.get(PATH + "/test.png"), ImageFormat.PNG);
        TimeCalculation.calculate();
    }
}
