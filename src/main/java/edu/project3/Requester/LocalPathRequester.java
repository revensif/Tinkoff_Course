package edu.project3.Requester;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LocalPathRequester implements LogRequester {

    @Override
    public List<Path> requestFiles(String path) {
        Path filePath = Paths.get(path);
        if (!Files.exists(filePath)) {
            return List.of();
        }
        if (Files.isRegularFile(filePath)) {
            return List.of(filePath);
        }
        if (Files.isDirectory(filePath)) {
            List<Path> pathList = new ArrayList<>();
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(filePath)) {
                for (Path paths : stream) {
                    if (Files.isRegularFile(paths)) {
                        pathList.add(paths);
                    }
                }
            } catch (IOException ioException) {
                throw new RuntimeException(ioException);
            }
            return pathList;
        }
        return List.of();
    }
}
