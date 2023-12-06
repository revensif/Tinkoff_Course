package edu.project4;

import edu.project4.Correction.GammaCorrection;
import edu.project4.Correction.ImageProcessor;
import edu.project4.Renderers.Renderer;
import edu.project4.Renderers.SingleThreadRenderer;
import edu.project4.Transformations.DiskTransformation;
import edu.project4.Transformations.HeartTransformation;
import edu.project4.Transformations.PolarTransformation;
import edu.project4.Transformations.SinusoidalTransformation;
import edu.project4.Transformations.SphericalTransformation;
import edu.project4.Transformations.Transformation;
import edu.project4.Utils.FractalImage;
import edu.project4.Utils.ImageFormat;
import edu.project4.Utils.ImageUtils;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public final class Main {
    private static final Path path = Paths.get("src/main/java/edu/project4/Flames");

    private Main() {
    }

    public static void main(String[] args) {
        FractalImage image = FractalImage.create(1920, 1080);
        Renderer renderer = new SingleThreadRenderer();
        List<Transformation> list = new ArrayList<>();
        list.add(new PolarTransformation());
        image = renderer.render(image, list, 20, 1000000, 10);
        ImageProcessor imageProcessor = new GammaCorrection(2.2);
        imageProcessor.process(image);
        ImageUtils.save(image, Paths.get(path + "/test.png"), ImageFormat.PNG);
    }
}
