package edu.project1;

import java.io.IOException;
import java.util.Random;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) throws IOException {
        new ConsoleHangman().run(new Random());
    }
}
