package edu.project4.Transformations;

import edu.project4.Utils.Point;

public class SphericalTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double divider = ((x * x) + (y * y));
        double newX = x / divider;
        double newY = y / divider;
        return new Point(newX, newY);
    }
}
