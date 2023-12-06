package edu.project4.Transformations;

import edu.project4.Utils.Point;

public class HeartTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double sqrt = Math.sqrt((x * x) + (y * y));
        double arcTan = Math.atan(y / x);
        double newX = sqrt * Math.sin(sqrt * arcTan);
        double newY = -sqrt * Math.cos(sqrt * arcTan);
        return new Point(newX, newY);
    }
}
