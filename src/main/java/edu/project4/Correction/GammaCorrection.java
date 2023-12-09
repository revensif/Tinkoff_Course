package edu.project4.Correction;

import edu.project4.Utils.FractalImage;
import edu.project4.Utils.Pixel;

public class GammaCorrection implements ImageProcessor {
    private final double gamma;

    public GammaCorrection(double gamma) {
        this.gamma = gamma;
    }

    @Override
    public void process(FractalImage image) {
        double max = normalizePixels(image);
        changeColors(image, max);
    }

    private double normalizePixels(FractalImage image) {
        double max = Double.MIN_VALUE;
        Pixel[][] pixels = image.data();
        for (int i = 0; i < image.width(); i++) {
            for (int j = 0; j < image.height(); j++) {
                Pixel pixel = pixels[i][j];
                if (pixel.getHitCount() > 0) {
                    pixel.setNormal(Math.log10(pixel.getHitCount()));
                    max = Math.max(max, pixel.getNormal());
                }
            }
        }
        return max;
    }

    private void changeColors(FractalImage image, double max) {
        Pixel[][] pixels = image.data();
        for (int i = 0; i < image.width(); i++) {
            for (int j = 0; j < image.height(); j++) {
                Pixel pixel = pixels[i][j];
                pixel.setNormal(pixel.getNormal() / max);
                double coefficient = Math.pow(pixel.getNormal(), 1.0 / gamma);
                pixel.setR((int) (pixel.getR() * coefficient));
                pixel.setG((int) (pixel.getG() * coefficient));
                pixel.setB((int) (pixel.getB() * coefficient));
            }
        }
    }
}
