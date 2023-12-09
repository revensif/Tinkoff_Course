package edu.hw6.Task3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

@FunctionalInterface
public interface AbstractFilter extends DirectoryStream.Filter<Path> {

    AbstractFilter REGULAR_FILE = Files::isRegularFile;
    AbstractFilter READABLE = Files::isReadable;
    AbstractFilter WRITABLE = Files::isWritable;

    default AbstractFilter and(AbstractFilter abstractFilter) {
        return (path) -> {
            try {
                return this.accept(path) && abstractFilter.accept(path);
            } catch (IOException ioException) {
                throw new IOException();
            }
        };
    }
}
