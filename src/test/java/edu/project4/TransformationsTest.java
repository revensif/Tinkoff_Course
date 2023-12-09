package edu.project4;

import edu.project4.Transformations.DiskTransformation;
import edu.project4.Transformations.HeartTransformation;
import edu.project4.Transformations.PolarTransformation;
import edu.project4.Transformations.SinusoidalTransformation;
import edu.project4.Transformations.SphericalTransformation;
import edu.project4.Transformations.Transformation;
import edu.project4.Utils.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TransformationsTest {

    @Test
    @DisplayName("DiskTransformation Test")
    void shouldTransformToDisk() {
        Point before = new Point(1.0, 1.0);
        Transformation diskTransformation = new DiskTransformation();
        Point after = diskTransformation.apply(before);
        assertThat(after.x()).isEqualTo(-0.24097563321246931);
        assertThat(after.y()).isEqualTo(-0.06656383551035391);
    }

    @Test
    @DisplayName("HeartTransformation Test")
    void shouldTransformToHeart() {
        Point before = new Point(1.0, 1.0);
        Transformation heartTransformation = new HeartTransformation();
        Point after = heartTransformation.apply(before);
        assertThat(after.x()).isEqualTo(1.2671621313307992);
        assertThat(after.y()).isEqualTo(-0.6279332232978174);
    }

    @Test
    @DisplayName("PolarTransformation Test")
    void shouldTransformToPolar() {
        Point before = new Point(1.0, 1.0);
        Transformation polarTransformation = new PolarTransformation();
        Point after = polarTransformation.apply(before);
        assertThat(after.x()).isEqualTo(0.25);
        assertThat(after.y()).isEqualTo(0.41421356237309515);
    }

    @Test
    @DisplayName("SinusoidalTransformation Test")
    void shouldTransformToSinusoidal() {
        Point before = new Point(1.0, 1.0);
        Transformation sinusoidalTransformation = new SinusoidalTransformation();
        Point after = sinusoidalTransformation.apply(before);
        assertThat(after.x()).isEqualTo(0.8414709848078965);
        assertThat(after.y()).isEqualTo(0.8414709848078965);
    }

    @Test
    @DisplayName("SphericalTransformation Test")
    void shouldTransformToSpherical() {
        Point before = new Point(1.0, 1.0);
        Transformation sphericalTransformation = new SphericalTransformation();
        Point after = sphericalTransformation.apply(before);
        assertThat(after.x()).isEqualTo(0.5);
        assertThat(after.y()).isEqualTo(0.5);
    }
}
