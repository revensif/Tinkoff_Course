package edu.hw3.Task5;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class Task5 {
    private static final String ASC = "ASC";
    private static final String DESC = "DESC";

    private Task5() {
    }

    public static List<Person> parseContacts(List<Person> list, String sort) {
        if (checkList(list, sort)) {
            return List.of();
        }
        if ((!sort.equals(ASC) && (!sort.equals(DESC)))) {
            throw new IllegalArgumentException("The second argument should be ASC or DESC");
        }
        if (sort.equals(ASC)) {
            return list.stream()
                .sorted(Comparator.comparing(Person::getLastName))
                .collect(Collectors.toList());
        } else {
            return list.stream()
                .sorted((o1, o2) -> o2.getLastName().compareTo(o1.getLastName()))
                .collect(Collectors.toList());
        }
    }

    private static boolean checkList(List<Person> list, String sort) {
        if ((list == null) || (list.isEmpty()) || (sort == null) || (sort.isEmpty())) {
            return true;
        }
        if (list.contains(null)) {
            return true;
        }
        for (Person person : list) {
            if (person.getLastName() == null) {
                return true;
            }
        }
        return false;
    }
}
