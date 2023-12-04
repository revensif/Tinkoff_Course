package edu.hw9.Task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;
import static edu.hw9.Task2.Files.Utils.checkDirectory;

public class DirectoriesFinder extends RecursiveTask<List<Path>> {
    private final Path currentDirectory;
    private final int minFilesInDirectory;

    public DirectoriesFinder(Path directory, int minFilesInDirectory) {
        if (checkDirectory(directory)) {
            throw new IllegalArgumentException();
        }
        currentDirectory = directory;
        this.minFilesInDirectory = minFilesInDirectory;
    }

    @Override
    public List<Path> compute() {
        List<Path> files = new ArrayList<>();
        List<DirectoriesFinder> forks = new ArrayList<>();
        AtomicLong counter = new AtomicLong(0);
        try (Stream<Path> pathStream = Files.list(currentDirectory)) {
            pathStream.forEach((file) -> {
                if (Files.isDirectory(file)) {
                    forks.add(new DirectoriesFinder(file, minFilesInDirectory));
                    forks.getLast().fork();
                } else {
                    counter.addAndGet(1);
                }
            });
            if (counter.get() >= minFilesInDirectory) {
                files.add(currentDirectory);
            }
            forks.forEach((filesFinder) -> files.addAll(filesFinder.join()));
            return files;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
