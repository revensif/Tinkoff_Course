package edu.hw6.Task2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public final class Task2 {

    private Task2() {
    }

    public static void cloneFile(Path path) throws IOException {
        File file = new File(path.toUri());
        if (!file.createNewFile()) {
            String fileName = file.getName().split("\\.")[0];
            String path2 = file.getParent() + "\\" + fileName;
            File copy = new File(path2 + " — копия.txt");
            if (!copy.createNewFile()) {
                int i = 2;
                while (true) {
                    File anotherCopy =
                        new File(path2 + " — копия " + "(" + i + ")" + ".txt");
                    if (!anotherCopy.createNewFile()) {
                        i++;
                    } else {
                        return;
                    }
                }
            }
        }
    }
}
