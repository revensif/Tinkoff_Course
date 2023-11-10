package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public final class Task2 {
    private static final int THIRTEENTH = 13;

    private Task2() {
    }

    public static List<LocalDate> getAllFridaysThe13Th(int year) {
        List<LocalDate> list = new ArrayList<>();
        if (year < 0) {
            throw new IllegalArgumentException("The year should be positive");
        }
        LocalDate date = LocalDate.of(year, 1, THIRTEENTH);
        while (date.getYear() == year) {
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                list.add(date);
            }
            date = date.plusMonths(1);
        }
        return list;
    }

    public static LocalDate nextFridayThe13Th(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("date is null");
        }
        LocalDate friday = date.plusDays(1);
        while (friday.getDayOfMonth() != THIRTEENTH) {
            friday = friday.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        }
        return friday;
    }
}
