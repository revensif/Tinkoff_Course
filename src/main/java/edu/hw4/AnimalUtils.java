package edu.hw4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public final class AnimalUtils {
    private static final int HEIGHT = 100;

    private AnimalUtils() {
    }

    public static List<Animal> sortHeightFromSmallestToTheLargest(List<Animal> animalList) {
        if ((animalList == null) || (animalList.isEmpty()) || (animalList.contains(null))) {
            return List.of();
        }
        return animalList.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .collect(Collectors.toList());
    }

    public static List<Animal> sortWeightFromHeaviestToTheLightestLimitedByFirstK(List<Animal> animalList, int k) {
        if ((animalList == null) || (animalList.isEmpty()) || (animalList.contains(null))) {
            return List.of();
        }
        return animalList.stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(k)
            .collect(Collectors.toList());
    }

    public static Map<Animal.Type, Integer> countAnimalOfEachType(List<Animal> animalList) {
        if ((animalList == null) || (animalList.isEmpty()) || (animalList.contains(null))) {
            return Map.of();
        }
        return animalList.stream()
            .collect(Collectors.toMap(Animal::type, value -> 1, Integer::sum));
    }

    public static Animal animalWithLongestName(List<Animal> animalList) {
        if ((animalList == null) || (animalList.isEmpty()) || (animalList.contains(null))) {
            return null;
        }
        return animalList.stream()
            .max(Comparator.comparingInt((animal) -> animal.name().length()))
            .get();
    }

    public static Animal.Sex whichAreMoreMOrF(List<Animal> animalList) {
        if ((animalList == null) || (animalList.isEmpty()) || (animalList.contains(null))) {
            return null;
        }
        return animalList.stream()
            .collect(Collectors.groupingBy(Animal::sex, Collectors.counting()))
            .entrySet().stream()
            .max(Comparator.comparingLong(Map.Entry::getValue))
            .get().getKey();
    }

    public static Map<Animal.Type, Integer> heaviestAnimalOfEachType(List<Animal> animalList) {
        if ((animalList == null) || (animalList.isEmpty()) || (animalList.contains(null))) {
            return Map.of();
        }
        return animalList.stream()
            .collect(Collectors.toMap(Animal::type, Animal::weight, Integer::max));
    }

    public static Animal oldestAnimalByIndexK(List<Animal> animalList, int k) {
        if ((animalList == null) || (animalList.isEmpty()) || (animalList.contains(null))) {
            return null;
        }
        return animalList.stream()
            .sorted(Comparator.comparingInt(Animal::age).reversed())
            .limit(k)
            .toList()
            .getLast();
    }

    public static Optional<Animal> heaviestAnimalThatIsLowerThanK(List<Animal> animalList, int k) {
        if ((animalList == null) || (animalList.isEmpty()) || (animalList.contains(null))) {
            return Optional.empty();
        }
        return animalList.stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .filter((animal) -> animal.height() < k)
            .findFirst();
    }

    public static Integer sumPawsOfEveryAnimal(List<Animal> animalList) {
        if ((animalList == null) || (animalList.isEmpty()) || (animalList.contains(null))) {
            return 0;
        }
        return animalList.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    public static List<Animal> listOfAnimalsWhoseAgeNotEqualToPaws(List<Animal> animalList) {
        if ((animalList == null) || (animalList.isEmpty()) || (animalList.contains(null))) {
            return List.of();
        }
        return animalList.stream()
            .filter((animal) -> animal.age() != animal.paws())
            .toList();
    }

    public static List<Animal> listOfAnimalsThatCanBiteAndHigherThan100(List<Animal> animalList) {
        if ((animalList == null) || (animalList.isEmpty()) || (animalList.contains(null))) {
            return List.of();
        }
        return animalList.stream()
            .filter((animal -> ((animal.height() > HEIGHT) && (animal.bites()))))
            .toList();
    }

    public static Long countEachAnimalWhoseWeightIsMoreThanHeight(List<Animal> animalList) {
        if ((animalList == null) || (animalList.isEmpty()) || (animalList.contains(null))) {
            return 0L;
        }
        return animalList.stream()
            .filter((animal) -> animal.weight() > animal.height())
            .count();
    }

    public static List<Animal> listOfAnimalsWhoseNameConsistOfMoreThan2Words(List<Animal> animalList) {
        if ((animalList == null) || (animalList.isEmpty()) || (animalList.contains(null))) {
            return List.of();
        }
        return animalList.stream()
            .filter((animal) -> animal.name().replaceAll("\\s+", " ").split(" ").length > 2)
            .toList();
    }

    public static Boolean isContainsDogThatIsHigherThanK(List<Animal> animalList, int k) {
        if ((animalList == null) || (animalList.isEmpty()) || (animalList.contains(null))) {
            return false;
        }
        return !animalList.stream()
            .filter((animal) -> ((animal.type() == Animal.Type.DOG) && (animal.height() > k)))
            .toList()
            .isEmpty();
    }

    public static Integer findWeightOfEveryAnimalThatAreFromKToIYearsLong(List<Animal> animalList, int k, int i) {
        if ((animalList == null) || (animalList.isEmpty()) || (animalList.contains(null))) {
            return 0;
        }
        if (k > i) {
            throw new IllegalArgumentException("k must be less that or equal to i");
        }
        return animalList.stream()
            .filter((animal) -> ((animal.age() >= k) && (animal.age() <= i)))
            .mapToInt(Animal::weight)
            .sum();
    }

    public static List<Animal> listOfAnimalsFilteredByTypeThanBySexThanByName(List<Animal> animalList) {
        if ((animalList == null) || (animalList.isEmpty()) || (animalList.contains(null))) {
            return List.of();
        }
        return animalList.stream()
            .sorted(Comparator.comparing(Animal::type).thenComparing(Animal::sex).thenComparing(Animal::name))
            .toList();
    }

    public static Boolean isItTrueThatSpidersBiteMoreThanDogs(List<Animal> animalList) {
        if ((animalList == null) || (animalList.isEmpty()) || (animalList.contains(null))) {
            return false;
        }
        return animalList.stream()
            .mapToInt((value) -> switch (value.type()) {
                case DOG -> (value.bites()) ? 1 : 0;
                case SPIDER -> (value.bites()) ? -1 : 0;
                default -> 0;
            })
            .sum() < 0;
    }

    @SafeVarargs public static Animal findHeaviestFishInLists(List<Animal>... animalLists) {
        if (animalLists.length < 2) {
            throw new IllegalArgumentException("There should be at least 2 lists");
        }
        List<Animal> animalList = Arrays.stream(animalLists).flatMap(List::stream).toList();
        if (animalList.isEmpty() || animalList.contains(null)) {
            return null;
        }
        return animalList.stream()
            .filter((animal) -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparingInt(Animal::weight))
            .get();
    }

    public static Map<String, Set<ValidationError>> animalsWithErrors(List<Animal> animalList) {
        if ((animalList == null) || (animalList.isEmpty()) || (animalList.contains(null))) {
            return Map.of();
        }
        return animalList.stream()
            .collect(Collectors.toMap(Animal::name, ValidationError::validateAnimal))
            .entrySet().stream()
            .filter((entry) -> !entry.getValue().isEmpty())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<String, String> animalsWithErrorsString(List<Animal> animalList) {
        if ((animalList == null) || (animalList.isEmpty()) || (animalList.contains(null))) {
            return Map.of();
        }
        return animalList.stream()
            .collect(Collectors.toMap(Animal::name, ValidationError::validateAnimalString))
            .entrySet().stream()
            .filter((entry) -> !entry.getValue().isEmpty())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
