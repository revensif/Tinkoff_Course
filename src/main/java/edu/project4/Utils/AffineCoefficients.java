package edu.project4.Utils;

import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

public record AffineCoefficients(double a, double b, double c, double d, double e, double f, Color color) {
    private static final double MAX_VALUE = 1.5;
    private static final int MAX_COLOR = 256;

    public static AffineCoefficients create() {
        while (true) {
            double a = ThreadLocalRandom.current().nextDouble(-1, 1);
            double b = ThreadLocalRandom.current().nextDouble(-1, 1);
            double c = ThreadLocalRandom.current().nextDouble(-1, 1);
            double d = ThreadLocalRandom.current().nextDouble(-1, 1);
            double e = ThreadLocalRandom.current().nextDouble(-MAX_VALUE, MAX_VALUE);
            double f = ThreadLocalRandom.current().nextDouble(-MAX_VALUE, MAX_VALUE);
            if (isCorrectCoefficients(a, b, d, e)) {
                int red = ThreadLocalRandom.current().nextInt(0, MAX_COLOR);
                int green = ThreadLocalRandom.current().nextInt(0, MAX_COLOR);
                int blue = ThreadLocalRandom.current().nextInt(0, MAX_COLOR);
                return new AffineCoefficients(a, b, c, d, e, f, new Color(red, green, blue));
            }
        }
    }

    private static boolean isCorrectCoefficients(double a, double b, double d, double e) {
        double sqrA = a * a;
        double sqrB = b * b;
        double sqrD = d * d;
        double sqrE = e * e;
        double aEBD = ((a * e) - (b * d));
        return (((sqrA + sqrD) < 1) && ((sqrB + sqrE) < 1) && ((sqrA + sqrB + sqrD + sqrE) < (1 + aEBD * aEBD)));
    }
}
