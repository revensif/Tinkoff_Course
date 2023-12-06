package edu.project4.Transformations;

import edu.project4.Utils.Point;

public class DiskTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double c1 = ((1 / Math.PI) * Math.atan(y / x));
        double c2 = Math.PI * Math.sqrt((x * x) + (y * y));
        double newX = c1 * Math.sin(c2);
        double newY = c1 * Math.cos(c2);
        return new Point(newX, newY);
    }
}
