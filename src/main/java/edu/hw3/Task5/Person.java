package edu.hw3.Task5;

import java.util.Objects;

public class Person {
    private String fullName;
    private String lastName;

    public Person(String fullName) {
        if (makeIncorrectName(fullName)) {
            return;
        }
        String[] name = fullName.split(" ");
        this.lastName = name[1];
        this.fullName = fullName;
    }

    public String getLastName() {
        return lastName;
    }

    private boolean makeIncorrectName(String fullName) {
        if ((fullName == null) || (fullName.trim().isEmpty())) {
            this.fullName = null;
            return true;
        }
        if (!fullName.matches(".*[a-zA-Z].*")) {
            throw new IllegalArgumentException("Full name should consist only of letters");
        }
        if (fullName.split(" ").length != 2) {
            this.lastName = fullName;
            this.fullName = fullName;
            return true;
        }
        return false;
    }

    @Override public String toString() {
        return fullName;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Person person)) {
            return false;
        }
        return Objects.equals(fullName, person.fullName) && Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, lastName);
    }
}
