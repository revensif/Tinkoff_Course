package edu.hw1;

public final class Task1 {
    private static final int SECONDS = 60;
    private static final String PATTERN_DIGIT = "\\d+";

    private Task1() {
    }

    public static int minutesToSeconds(String time) {
        boolean flag = false;
        if (time == null || time.isEmpty()) {
            return -1;
        }
        String[] line = time.split(":");
        if (line.length != 2) {
            flag = true;
        }
        if ((line[0].length() < 2) || (line[1].length() < 2)) {
            flag = true;
        }
        if (!(line[0].matches(PATTERN_DIGIT)) || !(line[1].matches(PATTERN_DIGIT))) {
            flag = true;
        }
        if (flag) {
            return -1;
        }
        int min = Integer.parseInt(line[0]);
        int sec = Integer.parseInt(line[1]);
        if (sec >= SECONDS) {
            flag = true;
        }
        if ((sec < 0) || (min < 0)) {
            flag = true;
        }
        return (flag) ? -1 : min * SECONDS + sec;
    }
}
