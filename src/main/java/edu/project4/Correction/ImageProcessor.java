package edu.project4.Correction;

import edu.project4.Utils.FractalImage;

@FunctionalInterface
public
interface ImageProcessor {
    void process(FractalImage image);
}
