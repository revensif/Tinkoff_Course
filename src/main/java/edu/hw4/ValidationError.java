package edu.hw4;

import java.util.HashSet;
import java.util.Set;

public enum ValidationError {

    NAME_ERROR,
    TYPE_ERROR,
    SEX_ERROR,
    AGE_ERROR,
    HEIGHT_ERROR,
    WEIGHT_ERROR;

    private static final int AGE_BOUND = 100;
    private static final String PATTERN = "^[a-zA-Z\\s]*$";

    public static Set<ValidationError> validateAnimal(Animal animal) {
        Set<ValidationError> set = new HashSet<>();
        if ((animal.name() == null) || (animal.name().trim().isEmpty()) || (!animal.name().matches(PATTERN))) {
            set.add(NAME_ERROR);
        }
        if (animal.type() == null) {
            set.add(TYPE_ERROR);
        }
        if (animal.sex() == null) {
            set.add(SEX_ERROR);
        }
        if ((animal.age() < 1) || (animal.age() > AGE_BOUND)) {
            set.add(AGE_ERROR);
        }
        if (animal.height() < 1) {
            set.add(HEIGHT_ERROR);
        }
        if (animal.weight() < 1) {
            set.add(WEIGHT_ERROR);
        }
        return set;
    }

    public static String validateAnimalString(Animal animal) {
        StringBuilder stringBuilder = new StringBuilder();
        if ((animal.name() == null) || (animal.name().trim().isEmpty()) || (!animal.name().matches(PATTERN))) {
            stringBuilder.append(NAME_ERROR).append(" ");
        }
        if (animal.type() == null) {
            stringBuilder.append(TYPE_ERROR).append(" ");
        }
        if (animal.sex() == null) {
            stringBuilder.append(SEX_ERROR).append(" ");
        }
        if ((animal.age() < 1) || (animal.age() > AGE_BOUND)) {
            stringBuilder.append(AGE_ERROR).append(" ");
        }
        if (animal.height() < 1) {
            stringBuilder.append(HEIGHT_ERROR).append(" ");
        }
        if (animal.weight() < 1) {
            stringBuilder.append(WEIGHT_ERROR).append(" ");
        }
        return String.valueOf(stringBuilder);
    }
}
