package edu.hw4;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw4.Animal.Sex.F;
import static edu.hw4.Animal.Sex.M;
import static edu.hw4.Animal.Type.BIRD;
import static edu.hw4.Animal.Type.CAT;
import static edu.hw4.Animal.Type.DOG;
import static edu.hw4.Animal.Type.FISH;
import static edu.hw4.Animal.Type.SPIDER;
import static edu.hw4.AnimalUtils.animalWithLongestName;
import static edu.hw4.AnimalUtils.animalsWithErrors;
import static edu.hw4.AnimalUtils.animalsWithErrorsString;
import static edu.hw4.AnimalUtils.countAnimalOfEachType;
import static edu.hw4.AnimalUtils.countEachAnimalWhoseWeightIsMoreThanHeight;
import static edu.hw4.AnimalUtils.findHeaviestFishInLists;
import static edu.hw4.AnimalUtils.findWeightOfEveryAnimalThatAreFromKToIYearsLong;
import static edu.hw4.AnimalUtils.heaviestAnimalOfEachType;
import static edu.hw4.AnimalUtils.heaviestAnimalThatIsLowerThanK;
import static edu.hw4.AnimalUtils.isContainsDogThatIsHigherThanK;
import static edu.hw4.AnimalUtils.isItTrueThatSpidersBiteMoreThanDogs;
import static edu.hw4.AnimalUtils.listOfAnimalsFilteredByTypeThanBySexThanByName;
import static edu.hw4.AnimalUtils.listOfAnimalsThatCanBiteAndHigherThan100;
import static edu.hw4.AnimalUtils.listOfAnimalsWhoseAgeNotEqualToPaws;
import static edu.hw4.AnimalUtils.listOfAnimalsWhoseNameConsistOfMoreThan2Words;
import static edu.hw4.AnimalUtils.oldestAnimalByIndexK;
import static edu.hw4.AnimalUtils.sortHeightFromSmallestToTheLargest;
import static edu.hw4.AnimalUtils.sortWeightFromHeaviestToTheLightestLimitedByFirstK;
import static edu.hw4.AnimalUtils.sumPawsOfEveryAnimal;
import static edu.hw4.AnimalUtils.whichAreMoreMOrF;
import static edu.hw4.ValidationError.AGE_ERROR;
import static edu.hw4.ValidationError.HEIGHT_ERROR;
import static edu.hw4.ValidationError.NAME_ERROR;
import static edu.hw4.ValidationError.SEX_ERROR;
import static edu.hw4.ValidationError.TYPE_ERROR;
import static edu.hw4.ValidationError.WEIGHT_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AnimalUtilsTest {

    @Test
    @DisplayName("Task1Test")
    void task1Test() {
        List<Animal> animalList = new ArrayList<>();
        Animal animal1 = new Animal("Fish", FISH, M, 4, 300, 10, true);
        Animal animal2 = new Animal("Dog", DOG, F, 7, 30, 20, false);
        Animal animal3 = new Animal("Cat", CAT, F, 1111, 150, 200, true);
        assertThat(sortHeightFromSmallestToTheLargest(animalList))
            .containsExactlyElementsOf(List.of());
        animalList.add(animal1);
        animalList.add(animal2);
        animalList.add(animal3);
        assertThat(sortHeightFromSmallestToTheLargest(animalList))
            .containsExactlyElementsOf(List.of(animal2, animal3, animal1));
    }

    @Test
    @DisplayName("Task2Test")
    void task2Test() {
        List<Animal> animalList = new ArrayList<>();
        Animal animal1 = new Animal("Fish", FISH, M, 4, 300, 100, true);
        Animal animal2 = new Animal("Dog", DOG, F, 7, 30, 20, false);
        Animal animal3 = new Animal("Cat", CAT, F, 1111, 150, 200, true);
        Animal animal4 = new Animal("Spider1", SPIDER, M, 87, 150, 150, true);
        Animal animal5 = new Animal("Spider2", SPIDER, F, 55, 154, 155, true);
        assertThat(sortWeightFromHeaviestToTheLightestLimitedByFirstK(animalList, 4))
            .containsExactlyElementsOf(List.of());
        animalList.add(animal1);
        animalList.add(animal2);
        animalList.add(animal3);
        animalList.add(animal4);
        animalList.add(animal5);
        assertThat(sortWeightFromHeaviestToTheLightestLimitedByFirstK(animalList, 4))
            .containsExactlyElementsOf(List.of(animal3, animal5, animal4, animal1));
        assertThat(sortWeightFromHeaviestToTheLightestLimitedByFirstK(animalList, 2))
            .containsExactlyElementsOf(List.of(animal3, animal5));
        assertThat(sortWeightFromHeaviestToTheLightestLimitedByFirstK(animalList, 6))
            .containsExactlyElementsOf(List.of(animal3, animal5, animal4, animal1, animal2));
    }

    @Test
    @DisplayName("Task3Test")
    void task3Test() {
        List<Animal> animalList = new ArrayList<>();
        Animal animal1 = new Animal("Fish", FISH, M, 4, 300, 100, true);
        Animal animal2 = new Animal("Dog", DOG, F, 7, 30, 20, false);
        Animal animal3 = new Animal("Cat", CAT, F, 1111, 150, 200, true);
        Animal animal4 = new Animal("Spider1", SPIDER, M, 87, 150, 150, true);
        Animal animal5 = new Animal("Spider2", SPIDER, F, 55, 154, 155, true);
        assertThat(countAnimalOfEachType(animalList))
            .containsExactlyInAnyOrderEntriesOf(Map.of());
        animalList.add(animal1);
        animalList.add(animal2);
        animalList.add(animal3);
        animalList.add(animal4);
        animalList.add(animal5);
        assertThat(countAnimalOfEachType(animalList))
            .containsExactlyInAnyOrderEntriesOf(Map.of(SPIDER, 2, CAT, 1, DOG, 1, FISH, 1));
    }

    @Test
    @DisplayName("Task4Test")
    void task4Test() {
        List<Animal> animalList = new ArrayList<>();
        Animal animal1 = new Animal("Fish", FISH, M, 4, 300, 100, true);
        Animal animal2 = new Animal("DogLongest", DOG, F, 7, 30, 20, false);
        Animal animal3 = new Animal("Cat", CAT, F, 1111, 150, 200, true);
        Animal animal4 = new Animal("Spider1", SPIDER, M, 87, 150, 150, true);
        Animal animal5 = new Animal("Spider2", SPIDER, F, 55, 154, 155, true);
        assertThat(animalWithLongestName(animalList)).isEqualTo(null);
        animalList.add(animal1);
        animalList.add(animal3);
        animalList.add(animal4);
        animalList.add(animal5);
        assertThat(animalWithLongestName(animalList)).isEqualTo(animal4);
        animalList.add(animal2);
        assertThat(animalWithLongestName(animalList)).isEqualTo(animal2);
    }

    @Test
    @DisplayName("Task5Test")
    void task5Test() {
        List<Animal> animalList = new ArrayList<>();
        Animal animal1 = new Animal("Fish", FISH, F, 4, 300, 100, true);
        Animal animal2 = new Animal("DogLongest", DOG, F, 7, 30, 20, false);
        Animal animal3 = new Animal("Cat", CAT, M, 1111, 150, 200, true);
        Animal animal4 = new Animal("Spider1", SPIDER, M, 87, 150, 150, true);
        Animal animal5 = new Animal("Spider2", SPIDER, F, 55, 154, 155, true);
        assertThat(whichAreMoreMOrF(animalList)).isEqualTo(null);
        animalList.add(animal1);
        animalList.add(animal2);
        animalList.add(animal3);
        assertThat(whichAreMoreMOrF(animalList)).isEqualTo(F);
        animalList.add(animal4);
        assertThat(whichAreMoreMOrF(animalList)).isEqualTo(M);
        animalList.add(animal5);
        assertThat(whichAreMoreMOrF(animalList)).isEqualTo(F);
    }

    @Test
    @DisplayName("Task6Test")
    void task6Test() {
        List<Animal> animalList = new ArrayList<>();
        Animal animal1 = new Animal("Fish1", FISH, M, 4, 300, 100, true);
        Animal animal2 = new Animal("Fish2", FISH, F, 23, 32, 1001, true);
        Animal animal3 = new Animal("Dog1", DOG, F, 72, 303, 20, false);
        Animal animal4 = new Animal("Dog2", DOG, M, 7, 30, 20, false);
        Animal animal5 = new Animal("Cat1", CAT, F, 1111, 150, 200, true);
        Animal animal6 = new Animal("Cat2", CAT, M, 123, 150, 152, true);
        Animal animal7 = new Animal("Spider1", SPIDER, M, 87, 150, 150, true);
        Animal animal8 = new Animal("Spider2", SPIDER, F, 55, 154, 155, true);
        assertThat(heaviestAnimalOfEachType(animalList))
            .containsExactlyInAnyOrderEntriesOf(Map.of());
        animalList.add(animal1);
        animalList.add(animal2);
        animalList.add(animal3);
        animalList.add(animal4);
        animalList.add(animal5);
        animalList.add(animal6);
        animalList.add(animal7);
        animalList.add(animal8);
        assertThat(heaviestAnimalOfEachType(animalList))
            .containsExactlyInAnyOrderEntriesOf(Map.of(SPIDER, 155, CAT, 200, DOG, 20, FISH, 1001));
    }

    @Test
    @DisplayName("Task7Test")
    void task7Test() {
        List<Animal> animalList = new ArrayList<>();
        Animal animal1 = new Animal("Fish", FISH, M, 40, 300, 100, true);
        Animal animal2 = new Animal("DogLongest", DOG, M, 7, 30, 20, false);
        Animal animal3 = new Animal("Cat", CAT, F, 1111, 150, 200, true);
        Animal animal4 = new Animal("Spider1", SPIDER, F, 87, 150, 150, true);
        Animal animal5 = new Animal("Spider2", SPIDER, F, 55, 154, 155, true);
        assertThat(oldestAnimalByIndexK(animalList, 4)).isEqualTo(null);
        animalList.add(animal1);
        animalList.add(animal2);
        animalList.add(animal3);
        animalList.add(animal4);
        animalList.add(animal5);
        assertThat(oldestAnimalByIndexK(animalList, 4)).isEqualTo(animal1);
        assertThat(oldestAnimalByIndexK(animalList, 1)).isEqualTo(animal3);
        assertThat(oldestAnimalByIndexK(animalList, 3)).isEqualTo(animal5);
        assertThat(oldestAnimalByIndexK(animalList, 5)).isEqualTo(animal2);
    }

    @Test
    @DisplayName("Task8Test")
    void task8Test() {
        List<Animal> animalList = new ArrayList<>();
        Animal animal1 = new Animal("Fish", FISH, M, 40, 152, 100, true);
        Animal animal2 = new Animal("DogLongest", DOG, M, 7, 30, 20, false);
        Animal animal3 = new Animal("Cat", CAT, F, 1111, 78, 200, true);
        Animal animal4 = new Animal("Spider1", SPIDER, F, 87, 150, 1500, true);
        Animal animal5 = new Animal("Spider2", SPIDER, F, 55, 154, 155, true);
        assertThat(heaviestAnimalThatIsLowerThanK(animalList, 234)).isEqualTo(Optional.empty());
        animalList.add(animal1);
        animalList.add(animal2);
        animalList.add(animal3);
        animalList.add(animal4);
        animalList.add(animal5);
        assertThat(heaviestAnimalThatIsLowerThanK(animalList, 153).get()).isEqualTo(animal4);
        assertThat(heaviestAnimalThatIsLowerThanK(animalList, 150).get()).isEqualTo(animal3);
        assertThat(heaviestAnimalThatIsLowerThanK(animalList, 60).get()).isEqualTo(animal2);
        assertThrows(NoSuchElementException.class, () -> heaviestAnimalThatIsLowerThanK(animalList, 20).get());
    }

    @Test
    @DisplayName("Task9Test")
    void task9Test() {
        List<Animal> animalList = new ArrayList<>();
        Animal animal1 = new Animal("Fish", FISH, M, 40, 152, 100, true);
        Animal animal2 = new Animal("DogLongest", DOG, M, 7, 30, 20, false);
        Animal animal3 = new Animal("Cat", CAT, F, 1111, 78, 200, true);
        Animal animal4 = new Animal("Spider1", SPIDER, F, 87, 150, 1500, true);
        Animal animal5 = new Animal("Spider2", SPIDER, F, 55, 154, 155, true);
        assertThat(sumPawsOfEveryAnimal(animalList)).isEqualTo(0);
        animalList.add(animal1);
        assertThat(sumPawsOfEveryAnimal(animalList)).isEqualTo(0);
        animalList.add(animal2);
        assertThat(sumPawsOfEveryAnimal(animalList)).isEqualTo(4);
        animalList.add(animal3);
        assertThat(sumPawsOfEveryAnimal(animalList)).isEqualTo(8);
        animalList.add(animal4);
        assertThat(sumPawsOfEveryAnimal(animalList)).isEqualTo(16);
        animalList.add(animal5);
        assertThat(sumPawsOfEveryAnimal(animalList)).isEqualTo(24);
    }

    @Test
    @DisplayName("Task10Test")
    void task10Test() {
        List<Animal> animalList = new ArrayList<>();
        Animal animal1 = new Animal("Fish", FISH, M, 3, 152, 100, true);
        Animal animal2 = new Animal("DogLongest", DOG, M, 4, 30, 20, false);
        Animal animal3 = new Animal("Cat", CAT, F, 7, 78, 200, true);
        Animal animal4 = new Animal("Spider1", SPIDER, F, 13, 150, 1500, true);
        Animal animal5 = new Animal("Spider2", SPIDER, F, 8, 154, 155, true);
        assertThat(listOfAnimalsWhoseAgeNotEqualToPaws(animalList)).isEqualTo(List.of());
        animalList.add(animal1);
        assertThat(listOfAnimalsWhoseAgeNotEqualToPaws(animalList))
            .containsExactlyInAnyOrder(animal1);
        animalList.add(animal2);
        assertThat(listOfAnimalsWhoseAgeNotEqualToPaws(animalList))
            .containsExactlyInAnyOrder(animal1);
        animalList.add(animal3);
        assertThat(listOfAnimalsWhoseAgeNotEqualToPaws(animalList))
            .containsExactlyInAnyOrder(animal1, animal3);
        animalList.add(animal4);
        assertThat(listOfAnimalsWhoseAgeNotEqualToPaws(animalList))
            .containsExactlyInAnyOrder(animal1, animal3, animal4);
        animalList.add(animal5);
        assertThat(listOfAnimalsWhoseAgeNotEqualToPaws(animalList))
            .containsExactlyInAnyOrder(animal1, animal3, animal4);
    }

    @Test
    @DisplayName("Task11Test")
    void task11Test() {
        List<Animal> animalList = new ArrayList<>();
        Animal animal1 = new Animal("Fish", FISH, M, 40, 152, 100, true);
        Animal animal2 = new Animal("DogLongest", DOG, M, 7, 300, 20, false);
        Animal animal3 = new Animal("Cat", CAT, F, 1111, 78, 200, true);
        Animal animal4 = new Animal("Spider1", SPIDER, F, 87, 100, 1500, true);
        Animal animal5 = new Animal("Spider2", SPIDER, F, 55, 154, 155, true);
        assertThat(listOfAnimalsThatCanBiteAndHigherThan100(animalList)).isEqualTo(List.of());
        animalList.add(animal1);
        animalList.add(animal2);
        animalList.add(animal3);
        animalList.add(animal4);
        animalList.add(animal5);
        assertThat(listOfAnimalsThatCanBiteAndHigherThan100(animalList))
            .containsExactlyInAnyOrder(animal1, animal5);
    }

    @Test
    @DisplayName("Task12Test")
    void task12Test() {
        List<Animal> animalList = new ArrayList<>();
        Animal animal1 = new Animal("Fish", FISH, M, 40, 152, 100, true);
        Animal animal2 = new Animal("DogLongest", DOG, M, 7, 300, 2000, false);
        Animal animal3 = new Animal("Cat", CAT, F, 1111, 78, 200, true);
        Animal animal4 = new Animal("Spider1", SPIDER, F, 87, 100, 15, true);
        Animal animal5 = new Animal("Spider2", SPIDER, F, 55, 154, 155, true);
        assertThat(countEachAnimalWhoseWeightIsMoreThanHeight(animalList)).isEqualTo(0);
        animalList.add(animal1);
        assertThat(countEachAnimalWhoseWeightIsMoreThanHeight(animalList)).isEqualTo(0);
        animalList.add(animal2);
        assertThat(countEachAnimalWhoseWeightIsMoreThanHeight(animalList)).isEqualTo(1);
        animalList.add(animal3);
        assertThat(countEachAnimalWhoseWeightIsMoreThanHeight(animalList)).isEqualTo(2);
        animalList.add(animal4);
        assertThat(countEachAnimalWhoseWeightIsMoreThanHeight(animalList)).isEqualTo(2);
        animalList.add(animal5);
        assertThat(countEachAnimalWhoseWeightIsMoreThanHeight(animalList)).isEqualTo(3);
    }

    @Test
    @DisplayName("Task13Test")
    void task13Test() {
        List<Animal> animalList = new ArrayList<>();
        Animal animal1 = new Animal("Fish 1 2", FISH, M, 40, 152, 100, true);
        Animal animal2 = new Animal("DogLongest     3", DOG, M, 7, 300, 2000, false);
        Animal animal3 = new Animal("Dog Test", CAT, F, 1111, 78, 200, true);
        Animal animal4 = new Animal("Spider1 3 Test 2", SPIDER, F, 87, 100, 15, true);
        Animal animal5 = new Animal("Spider2 Contains 3", SPIDER, F, 55, 154, 155, true);
        assertThat(listOfAnimalsWhoseNameConsistOfMoreThan2Words(animalList)).isEqualTo(List.of());
        animalList.add(animal1);
        assertThat(listOfAnimalsWhoseNameConsistOfMoreThan2Words(animalList))
            .containsExactlyInAnyOrder(animal1);
        animalList.add(animal2);
        assertThat(listOfAnimalsWhoseNameConsistOfMoreThan2Words(animalList))
            .containsExactlyInAnyOrder(animal1);
        animalList.add(animal3);
        assertThat(listOfAnimalsWhoseNameConsistOfMoreThan2Words(animalList))
            .containsExactlyInAnyOrder(animal1);
        animalList.add(animal4);
        assertThat(listOfAnimalsWhoseNameConsistOfMoreThan2Words(animalList))
            .containsExactlyInAnyOrder(animal1, animal4);
        animalList.add(animal5);
        assertThat(listOfAnimalsWhoseNameConsistOfMoreThan2Words(animalList))
            .containsExactlyInAnyOrder(animal1, animal4, animal5);
    }

    @Test
    @DisplayName("Task14Test")
    void task14Test() {
        List<Animal> animalList = new ArrayList<>();
        Animal animal1 = new Animal("Fish", FISH, M, 40, 152, 100, true);
        Animal animal2 = new Animal("DogLongest", DOG, M, 7, 300, 2000, false);
        Animal animal3 = new Animal("Dog", DOG, F, 1111, 78, 200, true);
        Animal animal4 = new Animal("Spider1", SPIDER, F, 87, 100, 15, true);
        Animal animal5 = new Animal("Dogster", DOG, F, 55, 154, 155, true);
        assertThat(isContainsDogThatIsHigherThanK(animalList, 70)).isFalse();
        animalList.add(animal1);
        animalList.add(animal2);
        animalList.add(animal3);
        animalList.add(animal4);
        animalList.add(animal5);
        assertThat(isContainsDogThatIsHigherThanK(animalList, 70)).isTrue();
        assertThat(isContainsDogThatIsHigherThanK(animalList, 100)).isTrue();
        assertThat(isContainsDogThatIsHigherThanK(animalList, 155)).isTrue();
        assertThat(isContainsDogThatIsHigherThanK(animalList, 3000)).isFalse();
    }

    @Test
    @DisplayName("Task15Test")
    void task15Test() {
        List<Animal> animalList = new ArrayList<>();
        Animal animal1 = new Animal("Fish", FISH, M, 40, 152, 100, true);
        Animal animal2 = new Animal("DogLongest", DOG, M, 7, 300, 2000, false);
        Animal animal3 = new Animal("Dog", DOG, F, 1111, 78, 200, true);
        Animal animal4 = new Animal("Spider1", SPIDER, F, 87, 100, 15, true);
        Animal animal5 = new Animal("Dogster", DOG, F, 55, 154, 155, true);
        assertThat(findWeightOfEveryAnimalThatAreFromKToIYearsLong(animalList, 1, 3)).isEqualTo(0);
        animalList.add(animal1);
        assertThrows(
            IllegalArgumentException.class,
            () -> findWeightOfEveryAnimalThatAreFromKToIYearsLong(animalList, 4, 3)
        );
        animalList.add(animal2);
        animalList.add(animal3);
        animalList.add(animal4);
        animalList.add(animal5);
        assertThat(findWeightOfEveryAnimalThatAreFromKToIYearsLong(animalList, 7, 55)).isEqualTo(2255);
        assertThat(findWeightOfEveryAnimalThatAreFromKToIYearsLong(animalList, 55, 1111)).isEqualTo(370);
        assertThat(findWeightOfEveryAnimalThatAreFromKToIYearsLong(animalList, 1112, 2000)).isEqualTo(0);
    }

    @Test
    @DisplayName("Task16Test")
    void task16Test() {
        List<Animal> animalList = new ArrayList<>();
        Animal animal1 = new Animal("Fish", FISH, M, 40, 152, 100, true);
        Animal animal2 = new Animal("Dogster", DOG, M, 7, 300, 2000, false);
        Animal animal3 = new Animal("Cat", CAT, F, 1111, 78, 200, true);
        Animal animal4 = new Animal("Spider1", SPIDER, F, 87, 100, 15, false);
        Animal animal5 = new Animal("DogLongest", DOG, M, 55, 154, 155, true);
        Animal animal6 = new Animal("Bird", BIRD, F, 25, 134, 192, false);
        assertThat(listOfAnimalsFilteredByTypeThanBySexThanByName(animalList)).isEqualTo(List.of());
        animalList.add(animal1);
        animalList.add(animal2);
        animalList.add(animal3);
        animalList.add(animal4);
        animalList.add(animal5);
        animalList.add(animal6);
        assertThat(listOfAnimalsFilteredByTypeThanBySexThanByName(animalList))
            .containsExactly(animal3, animal5, animal2, animal6, animal1, animal4);
    }

    @Test
    @DisplayName("Task17Test")
    void task17Test() {
        List<Animal> animalList = new ArrayList<>();
        Animal animal1 = new Animal("Fish", FISH, M, 40, 152, 100, true);
        Animal animal2 = new Animal("Dogster", DOG, M, 7, 300, 2000, false);
        Animal animal3 = new Animal("Cat", CAT, F, 1111, 78, 200, true);
        Animal animal4 = new Animal("Spider1", SPIDER, F, 87, 100, 15, true);
        Animal animal5 = new Animal("DogLongest", DOG, M, 55, 154, 155, true);
        Animal animal6 = new Animal("Spider2", SPIDER, M, 25, 134, 192, true);
        assertThat(isItTrueThatSpidersBiteMoreThanDogs(animalList)).isEqualTo(false);
        animalList.add(animal1);
        assertThat(isItTrueThatSpidersBiteMoreThanDogs(animalList)).isEqualTo(false);
        animalList.add(animal2);
        assertThat(isItTrueThatSpidersBiteMoreThanDogs(animalList)).isEqualTo(false);
        animalList.add(animal3);
        assertThat(isItTrueThatSpidersBiteMoreThanDogs(animalList)).isEqualTo(false);
        animalList.add(animal4);
        assertThat(isItTrueThatSpidersBiteMoreThanDogs(animalList)).isEqualTo(true);
        animalList.add(animal5);
        assertThat(isItTrueThatSpidersBiteMoreThanDogs(animalList)).isEqualTo(false);
        animalList.add(animal6);
        assertThat(isItTrueThatSpidersBiteMoreThanDogs(animalList)).isEqualTo(true);
    }

    @Test
    @DisplayName("Task18Test")
    void task18Test() {
        List<Animal> animalList1 = new ArrayList<>();
        List<Animal> animalList2 = new ArrayList<>();
        Animal animal1 = new Animal("Fish1", FISH, M, 40, 152, 100, true);
        Animal animal2 = new Animal("Dogster", DOG, M, 7, 300, 2000, false);
        Animal animal3 = new Animal("Cat", CAT, F, 1111, 78, 200, true);
        Animal animal4 = new Animal("Fish2", FISH, F, 87, 100, 1500, false);
        Animal animal5 = new Animal("DogLongest", DOG, M, 55, 154, 155, true);
        Animal animal6 = new Animal("Fish3", FISH, M, 25, 134, 1920, false);
        assertThrows(IllegalArgumentException.class, () -> findHeaviestFishInLists(animalList1));
        assertThrows(IllegalArgumentException.class, () -> findHeaviestFishInLists(animalList2));
        assertThat(findHeaviestFishInLists(animalList1, animalList2)).isEqualTo(null);
        animalList1.add(animal1);
        animalList1.add(animal2);
        animalList1.add(animal3);
        animalList2.add(animal5);
        assertThat(findHeaviestFishInLists(animalList1, animalList2)).isEqualTo(animal1);
        animalList2.add(animal4);
        assertThat(findHeaviestFishInLists(animalList1, animalList2)).isEqualTo(animal4);
        animalList2.add(animal6);
        assertThat(findHeaviestFishInLists(animalList1, animalList2)).isEqualTo(animal6);
    }

    @Test
    @DisplayName("Task19Test")
    void task19Test() {
        List<Animal> animalList = new ArrayList<>();
        Animal animal1 = new Animal("Fish", FISH, M, 40, 152, 100, true);
        Animal animal2 = new Animal("DogLongest", DOG, null, -12, -13, 2000, false);
        Animal animal3 = new Animal("Dog", DOG, F, 1111, 78, -200, true);
        Animal animal4 = new Animal("Spider", SPIDER, F, 87, 100, 15, true);
        Animal animal5 = new Animal("Dogster1", null, F, 55, 154, 155, true);
        assertThat(animalsWithErrors(animalList))
            .containsExactlyInAnyOrderEntriesOf(Map.of());
        animalList.add(animal1);
        animalList.add(animal2);
        animalList.add(animal3);
        animalList.add(animal4);
        animalList.add(animal5);
        assertThat(animalsWithErrors(animalList))
            .containsExactlyInAnyOrderEntriesOf(Map.of(
                "DogLongest", Set.of(SEX_ERROR, AGE_ERROR, HEIGHT_ERROR),
                "Dog", Set.of(AGE_ERROR, WEIGHT_ERROR),
                "Dogster1", Set.of(NAME_ERROR, TYPE_ERROR)
            ));
    }

    @Test
    @DisplayName("Task20Test")
    void task20Test() {
        List<Animal> animalList = new ArrayList<>();
        Animal animal1 = new Animal("Fish", FISH, M, 40, 152, 100, true);
        Animal animal2 = new Animal("DogLongest", DOG, null, -12, -13, 2000, false);
        Animal animal3 = new Animal("Dog", DOG, F, 1111, 78, -200, true);
        Animal animal4 = new Animal("Spider", SPIDER, F, 87, 100, 15, true);
        Animal animal5 = new Animal("Dogster1", null, F, 55, 154, 155, true);
        assertThat(animalsWithErrorsString(animalList))
            .containsExactlyInAnyOrderEntriesOf(Map.of());
        animalList.add(animal1);
        animalList.add(animal2);
        animalList.add(animal3);
        animalList.add(animal4);
        animalList.add(animal5);
        assertThat(animalsWithErrorsString(animalList))
            .containsExactlyInAnyOrderEntriesOf(Map.of(
                "DogLongest", "SEX_ERROR AGE_ERROR HEIGHT_ERROR ",
                "Dog", "AGE_ERROR WEIGHT_ERROR ",
                "Dogster1", "NAME_ERROR TYPE_ERROR "
            ));
    }
}
