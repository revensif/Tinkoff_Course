package edu.project1;

import java.util.Random;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        new ConsoleHangman().run(new Random());
    }
}
