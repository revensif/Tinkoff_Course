package edu.hw6.Task3;

import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;

public interface GlobAbstractFilter extends AbstractFilter {

    static AbstractFilter globMatches(String globString) {
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + globString);
        return (path) -> pathMatcher.matches(path.getFileName());
    }
}
