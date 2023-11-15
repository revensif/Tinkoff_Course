package edu.hw6.Task3;

import java.nio.file.Files;

public interface MagicNumberAbstractFilter extends AbstractFilter {

    static AbstractFilter magicNumber(byte... bytes) {
        return (path) -> {
            byte[] fileBytes = Files.readAllBytes(path);
            if (fileBytes.length < bytes.length) {
                return false;
            }
            for (int i = 0; i < bytes.length; i++) {
                if (fileBytes[i] != bytes[i]) {
                    return false;
                }
            }
            return true;
        };
    }
}
