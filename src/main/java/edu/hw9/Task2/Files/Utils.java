package edu.hw9.Task2.Files;

import java.nio.file.Files;
import java.nio.file.Path;

public final class Utils {

    private Utils() {
    }

    public static boolean checkDirectory(Path directory) {
        return ((directory == null) || (!Files.exists(directory)));
    }
}
