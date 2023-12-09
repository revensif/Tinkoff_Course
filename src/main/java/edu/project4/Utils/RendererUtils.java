package edu.project4.Utils;

import edu.project4.Transformations.Transformation;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class RendererUtils {
    public static final Point MIN_POINT = new Point(-1.777, -1.0);
    public static final Point MAX_POINT = new Point(1.777, 1.0);
    public static final double X_RANGE = MAX_POINT.x() - MIN_POINT.x();
    public static final double Y_RANGE = MAX_POINT.y() - MIN_POINT.y();
    public static final int FIRST_ITERATION = 20;
    public static final int AFFINE_NUMBER = 8;

    private RendererUtils() {
    }

    public static Pixel[][] createPixels(int width, int height) {
        Pixel[][] data = new Pixel[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                data[i][j] = new Pixel(0, 0, 0, 0, 0);
            }
        }
        return data;
    }

    public static AffineCoefficients[] generateAffineCoefficients() {
        AffineCoefficients[] coefficients = new AffineCoefficients[AFFINE_NUMBER];
        for (int i = 0; i < AFFINE_NUMBER; i++) {
            coefficients[i] = AffineCoefficients.create();
        }
        return coefficients;
    }

    public static Point getRandomPoint() {
        double x = ThreadLocalRandom.current().nextDouble(MIN_POINT.x(), MAX_POINT.x());
        double y = ThreadLocalRandom.current().nextDouble(MIN_POINT.y(), MAX_POINT.y());
        return new Point(x, y);
    }

    public static AffineCoefficients getRandomAffineCoefficient(AffineCoefficients[] affineCoefficients) {
        int index = ThreadLocalRandom.current().nextInt(0, AFFINE_NUMBER);
        return affineCoefficients[index];
    }

    public static Point transformPointWithAffineCoefficient(AffineCoefficients coefficient, Point oldPoint) {
        double newX = coefficient.a() * oldPoint.x() + coefficient.b() * oldPoint.y() + coefficient.c();
        double newY = coefficient.d() * oldPoint.x() + coefficient.e() * oldPoint.y() + coefficient.f();
        return new Point(newX, newY);
    }

    public static Transformation getRandomTransformation(List<Transformation> variations) {
        int index = ThreadLocalRandom.current().nextInt(0, variations.size());
        return variations.get(index);
    }

    public static Point getRotatedPoint(Point point, double theta) {
        double newX = point.x() * Math.cos(theta) - point.y() * Math.sin(theta);
        double newY = point.x() * Math.sin(theta) + point.y() * Math.cos(theta);
        return new Point(newX, newY);
    }

    public static boolean isPointInRightArea(Point point) {
        return ((point.x() >= MIN_POINT.x()) && (point.x() <= MAX_POINT.x())
            && (point.y() >= MIN_POINT.y()) && (point.y() <= MAX_POINT.y()));
    }

    public static Pixel changePixel(Pixel pixel, AffineCoefficients coefficient) {
        int r = 0;
        int g = 0;
        int b = 0;
        if (pixel.getHitCount() == 0) {
            r = coefficient.color().getRed();
            g = coefficient.color().getGreen();
            b = coefficient.color().getBlue();
        } else {
            r = ((pixel.getR() + coefficient.color().getRed()) / 2);
            g = ((pixel.getG() + coefficient.color().getGreen()) / 2);
            b = ((pixel.getB() + coefficient.color().getBlue()) / 2);
        }
        return new Pixel(r, g, b, pixel.getHitCount() + 1, pixel.getNormal());
    }
}
