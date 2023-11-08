package edu.hw3.Task5;

import java.util.Objects;

public class Person {
    private String firstName;
    private String lastName;

    public Person(String fullName) {
        if (makeIncorrectName(fullName)) {
            return;
        }
        String[] name = fullName.split(" ");
        if (name.length != 2) {
            throw new IllegalArgumentException("Incorrect Full Name (Example: Thomas Aquinas)");
        }
        this.firstName = name[0];
        this.lastName = name[1];
    }

    public String getLastName() {
        return lastName;
    }

    private boolean makeIncorrectName(String fullName) {
        if ((fullName == null) || (fullName.trim().isEmpty())) {
            this.firstName = null;
            this.lastName = null;
            return true;
        }
        if (!fullName.matches(".*[a-zA-Z].*")) {
            throw new IllegalArgumentException("Full name should consist only of letters");
        }
        if (fullName.split(" ").length == 1) {
            this.firstName = "";
            this.lastName = fullName;
            return true;
        }
        return false;
    }

    @Override public String toString() {
        return firstName + " " + lastName;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
