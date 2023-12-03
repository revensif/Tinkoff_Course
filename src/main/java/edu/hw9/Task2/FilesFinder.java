package edu.hw9.Task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Stream;
import static edu.hw9.Task2.Files.Utils.checkDirectory;

public class FilesFinder extends RecursiveTask<List<Path>> {
    private static final String EXTENSION_PATTERN = ".*\\.";
    private final Path currentDirectory;
    private final long minFileSize;
    private final long maxFileSize;
    private final String fileExtension;

    public FilesFinder(Path directory, long minFileSize, long maxFileSize, String fileExtension) {
        if ((checkDirectory(directory)) || (minFileSize > maxFileSize)) {
            throw new IllegalArgumentException();
        }
        currentDirectory = directory;
        this.minFileSize = minFileSize;
        this.maxFileSize = maxFileSize;
        this.fileExtension = fileExtension;
    }

    @Override
    public List<Path> compute() {
        List<Path> files = new ArrayList<>();
        List<FilesFinder> forks = new ArrayList<>();
        try (Stream<Path> pathStream = Files.list(currentDirectory)) {
            pathStream.forEach((file) -> {
                if (Files.isDirectory(file)) {
                    forks.add(new FilesFinder(file, minFileSize, maxFileSize, fileExtension));
                    forks.getLast().fork();
                } else {
                    if (isFileCorrect(file)) {
                        files.add(file);
                    }
                }
            });
            forks.forEach((filesFinder) -> files.addAll(filesFinder.join()));
            return files;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isFileCorrect(Path file) {
        try {
            return (isCorrectSize(file) && (isCorrectExtension(file)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isCorrectSize(Path file) throws IOException {
        long size = Files.size(file);
        return ((size >= minFileSize) && (size <= maxFileSize));
    }

    private boolean isCorrectExtension(Path file) {
        return ((fileExtension == null) || (file.getFileName().toString().matches(EXTENSION_PATTERN + fileExtension)));
    }
}
