package edu.project4.Renderers;

import edu.project4.Transformations.Transformation;
import edu.project4.Utils.FractalImage;
import java.util.List;

@FunctionalInterface
public interface Renderer {
    FractalImage render(
        FractalImage canvas, List<Transformation> variations, int samples, int iterPerSample, int symmetry
    );
}
