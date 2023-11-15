package edu.hw6.Task3;

public interface WeightAbstractFilter extends AbstractFilter {

    static AbstractFilter largerThan(long bytes) {
        return (path) -> path.toFile().length() > bytes;
    }

    static AbstractFilter lessThan(long bytes) {
        return (path) -> path.toFile().length() < bytes;
    }
}
