package edu.project4.Utils;

import edu.project4.Renderers.MultipleThreadRenderer;
import edu.project4.Renderers.Renderer;
import edu.project4.Renderers.SingleThreadRenderer;
import edu.project4.Transformations.SphericalTransformation;
import edu.project4.Transformations.Transformation;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static edu.project4.Utils.ImageUtils.HEIGHT;
import static edu.project4.Utils.ImageUtils.WIDTH;

public final class TimeCalculation {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int[] THREADS = new int[] {2, 4, 6, 8};
    private static final int SAMPLES = 20;
    private static final int SIMULATION_NUMBER = 30;
    private static final int ITER_PER_SAMPLE = 1000000;
    private static final int SYMMETRY = 3;
    private static final int CONVERT = 1_000_000;
    private static final int LINE_LENGTH = 20;

    private TimeCalculation() {
    }

    public static void calculate() {
        FractalImage fractalImage = FractalImage.create(WIDTH, HEIGHT);
        List<Transformation> variations = new ArrayList<>();
        variations.add(new SphericalTransformation());
        Renderer renderer = new SingleThreadRenderer();
        double averageTime = 0;
        for (int i = 0; i < SIMULATION_NUMBER; i++) {
            double startTime = System.nanoTime();
            renderer.render(fractalImage, variations, SAMPLES, ITER_PER_SAMPLE, SYMMETRY);
            double endTime = System.nanoTime();
            averageTime += (endTime - startTime) / CONVERT;
        }
        LOGGER.info("Average time for single thread = {} ms", averageTime / SIMULATION_NUMBER);
        LOGGER.info("-".repeat(LINE_LENGTH));
        for (int thread : THREADS) {
            averageTime = 0;
            Renderer multiThreadRenderer = new MultipleThreadRenderer(thread);
            for (int i = 0; i < SIMULATION_NUMBER; i++) {
                double startTime = System.nanoTime();
                multiThreadRenderer.render(fractalImage, variations, SAMPLES, ITER_PER_SAMPLE, SYMMETRY);
                double endTime = System.nanoTime();
                averageTime += (endTime - startTime) / CONVERT;
            }
            LOGGER.info("Average time for {} threads = {} ms", thread, averageTime / SIMULATION_NUMBER);
            LOGGER.info("-".repeat(LINE_LENGTH));
        }
    }
}
