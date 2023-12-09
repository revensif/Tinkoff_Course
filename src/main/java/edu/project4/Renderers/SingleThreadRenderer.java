package edu.project4.Renderers;

import edu.project4.Transformations.Transformation;
import edu.project4.Utils.AffineCoefficients;
import edu.project4.Utils.FractalImage;
import edu.project4.Utils.Pixel;
import edu.project4.Utils.Point;
import java.util.List;
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

public class SingleThreadRenderer implements Renderer {
    private final AffineCoefficients[] affineCoefficients = generateAffineCoefficients();

    @Override
    public FractalImage render(
        FractalImage canvas, List<Transformation> variations, int samples, int iterPerSample, int symmetry
    ) {
        int xRes = canvas.width();
        int yRes = canvas.height();
        Pixel[][] pixels = createPixels(xRes, yRes);
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
                                pixels[x1][y1] = changePixel(pixels[x1][y1], coefficient);
                            }
                        }
                    }
                }
            }
        }
        return new FractalImage(pixels, xRes, yRes);
    }
}
