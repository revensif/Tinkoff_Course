package edu.hw6.Task3;

public interface RegexAbstractFilter extends AbstractFilter {

    static AbstractFilter regexContains(String regex) {
        return (path) -> path.getFileName().toString().matches(".*" + regex + ".*");
    }
}
