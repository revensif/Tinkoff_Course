package edu.project4.Renderers;

import edu.project4.Transformations.Transformation;
import edu.project4.Utils.AffineCoefficients;
import edu.project4.Utils.FractalImage;
import edu.project4.Utils.Pixel;
import edu.project4.Utils.Point;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import static edu.project4.Utils.RendererUtils.FIRST_ITERATION;
import static edu.project4.Utils.RendererUtils.MAX_POINT;
import static edu.project4.Utils.RendererUtils.X_RANGE;
import static edu.project4.Utils.RendererUtils.Y_RANGE;
import static edu.project4.Utils.RendererUtils.changePixel;
import static edu.project4.Utils.RendererUtils.createPixels;
import static edu.project4.Utils.RendererUtils.generateAffineCoefficients;
import static edu.project4.Utils.RendererUtils.getRandomAffineCoefficient;
import static edu.project4.Utils.RendererUtils.getRandomPoint;
import static edu.project4.Utils.RendererUtils.getRandomTransformation;
import static edu.project4.Utils.RendererUtils.getRotatedPoint;
import static edu.project4.Utils.RendererUtils.isPointInRightArea;
import static edu.project4.Utils.RendererUtils.transformPointWithAffineCoefficient;

public class MultipleThreadRenderer implements Renderer {
    private static final long MINUTES = 1;
    private final AffineCoefficients[] affineCoefficients = generateAffineCoefficients();
    private final int threadNumber;
    private Pixel[][] pixels;
    private final AtomicInteger samples = new AtomicInteger(0);

    public MultipleThreadRenderer(int threadNumber) {
        this.threadNumber = threadNumber;
    }

    @Override
    public FractalImage render(
        FractalImage canvas, List<Transformation> variations, int samples, int iterPerSample, int symmetry
    ) {
        ExecutorService executor = Executors.newFixedThreadPool(threadNumber);
        int xRes = canvas.width();
        int yRes = canvas.height();
        pixels = createPixels(xRes, yRes);
        executor.execute(() -> doIterations(xRes, yRes, variations, samples / threadNumber, iterPerSample, symmetry));
        executor.shutdown();
        try {
            executor.awaitTermination(MINUTES, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new FractalImage(pixels, xRes, yRes);
    }

    private void doIterations(
        int xRes, int yRes, List<Transformation> variations, int samples, int iterPerSample, int symmetry
    ) {
        for (int num = 0; num < samples; num++) {
            Point point = getRandomPoint();
            for (int step = -FIRST_ITERATION; step < iterPerSample; step++) {
                AffineCoefficients coefficient = getRandomAffineCoefficient(affineCoefficients);
                point = transformPointWithAffineCoefficient(coefficient, point);
                Transformation transformation = getRandomTransformation(variations);
                point = transformation.apply(point);
                if (step >= 0) {
                    double theta = 0.0;
                    for (int i = 0; i < symmetry; i++) {
                        theta += ((2 * Math.PI) / symmetry);
                        Point rotated = getRotatedPoint(point, theta);
                        if (isPointInRightArea(rotated)) {
                            int x1 = xRes - (int) (((MAX_POINT.x() - rotated.x()) / X_RANGE) * xRes);
                            int y1 = yRes - (int) (((MAX_POINT.y() - rotated.y()) / Y_RANGE) * yRes);
                            if ((x1 < xRes) && (y1 < yRes)) {
                                synchronized (pixels[x1][y1]) {
                                    pixels[x1][y1] = changePixel(pixels[x1][y1], coefficient);
                                }
                            }
                        }
                    }
                }
            }
        }
        this.samples.addAndGet(samples);
    }
}
