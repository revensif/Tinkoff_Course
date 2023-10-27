package edu.hw3;

import java.util.ArrayList;
import java.util.List;

public final class Task2 {
    private static final char LEFT_BRACKET = '(';
    private static final char RIGHT_BRACKET = ')';
    private static final String UNBALANCED = "The cluster is unbalanced";

    private Task2() {
    }

    public static List<String> clusterize(String text) {
        if ((text == null) || (text.trim().isEmpty())) {
            return List.of("");
        }
        if (text.length() % 2 != 0) {
            throw new IllegalArgumentException("Clusters must be of even length");
        }
        int count = 0;
        List<String> list = new ArrayList<>();
        StringBuilder cluster = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            switch (text.charAt(i)) {
                case (LEFT_BRACKET) -> {
                    count++;
                    cluster.append(LEFT_BRACKET);
                }
                case (RIGHT_BRACKET) -> {
                    count--;
                    cluster.append(RIGHT_BRACKET);
                }
                default -> throw new IllegalArgumentException("There should be only brackets in the cluster");
            }
            if (count < 0) {
                throw new IllegalArgumentException(UNBALANCED);
            }
            if (count == 0) {
                list.add(String.valueOf(cluster));
                cluster.setLength(0);
            }
        }
        if (count > 0) {
            throw new IllegalArgumentException(UNBALANCED);
        }
        return list;
    }
}
