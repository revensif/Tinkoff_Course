package edu.hw4;

import java.util.HashSet;
import java.util.Set;

public enum ValidationError {

    NAME_IS_EMPTY,
    NAME_HAS_INCORRECT_SYMBOLS,
    TYPE_IS_NULL,
    SEX_IS_NULL,
    AGE_IS_LOWER_THAN_1,
    AGE_IS_HIGHER_THAN_AGE_BOUND,
    HEIGHT_IS_LOWER_THAN_1,
    WEIGHT_IS_LOWER_THAN_1;

    private static final int AGE_BOUND = 100;
    private static final String PATTERN = "^[a-zA-Z\\s]*$";

    public static Set<ValidationError> validateAnimal(Animal animal) {
        Set<ValidationError> set = new HashSet<>();
        if (checkNameNullOrEmpty(animal)) {
            set.add(NAME_IS_EMPTY);
        }
        if (checkNameIncorrectSymbols(animal)) {
            set.add(NAME_HAS_INCORRECT_SYMBOLS);
        }
        if (checkType(animal)) {
            set.add(TYPE_IS_NULL);
        }
        if (checkSex(animal)) {
            set.add(SEX_IS_NULL);
        }
        if (checkAgeHighBound(animal)) {
            set.add(AGE_IS_HIGHER_THAN_AGE_BOUND);
        }
        if (checkAgeIsLowerThan1(animal)) {
            set.add(AGE_IS_LOWER_THAN_1);
        }
        if (checkHeight(animal)) {
            set.add(HEIGHT_IS_LOWER_THAN_1);
        }
        if (checkWeight(animal)) {
            set.add(WEIGHT_IS_LOWER_THAN_1);
        }
        return set;
    }

    public static String validateAnimalString(Animal animal) {
        StringBuilder stringBuilder = new StringBuilder();
        if (checkNameNullOrEmpty(animal)) {
            stringBuilder.append(NAME_IS_EMPTY).append(" ");
        }
        if (checkNameIncorrectSymbols(animal)) {
            stringBuilder.append(NAME_HAS_INCORRECT_SYMBOLS).append(" ");
        }
        if (checkType(animal)) {
            stringBuilder.append(TYPE_IS_NULL).append(" ");
        }
        if (checkSex(animal)) {
            stringBuilder.append(SEX_IS_NULL).append(" ");
        }
        if (checkAgeHighBound(animal)) {
            stringBuilder.append(AGE_IS_HIGHER_THAN_AGE_BOUND).append(" ");
        }
        if (checkAgeIsLowerThan1(animal)) {
            stringBuilder.append(AGE_IS_LOWER_THAN_1).append(" ");
        }
        if (checkHeight(animal)) {
            stringBuilder.append(HEIGHT_IS_LOWER_THAN_1).append(" ");
        }
        if (checkWeight(animal)) {
            stringBuilder.append(WEIGHT_IS_LOWER_THAN_1).append(" ");
        }
        return String.valueOf(stringBuilder);
    }

    private static boolean checkNameNullOrEmpty(Animal animal) {
        return ((animal.name() == null) || (animal.name().trim().isEmpty()));
    }

    private static boolean checkNameIncorrectSymbols(Animal animal) {
        if (animal.name() == null) {
            return false;
        }
        return (!animal.name().matches(PATTERN));
    }

    private static boolean checkType(Animal animal) {
        return (animal.type() == null);
    }

    private static boolean checkSex(Animal animal) {
        return (animal.sex() == null);
    }

    private static boolean checkAgeHighBound(Animal animal) {
        return (animal.age() > AGE_BOUND);
    }

    private static boolean checkAgeIsLowerThan1(Animal animal) {
        return (animal.age() < 1);
    }

    private static boolean checkHeight(Animal animal) {
        return (animal.height() < 1);
    }

    private static boolean checkWeight(Animal animal) {
        return (animal.weight() < 1);
    }
}
