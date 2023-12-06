package edu.project4.Utils;

import static edu.project4.Utils.RendererUtils.createPixels;

public record FractalImage(Pixel[][] data, int width, int height) {

    public static FractalImage create(int width, int height) {
        Pixel[][] data = createPixels(width, height);
        return new FractalImage(data, width, height);
    }
}
