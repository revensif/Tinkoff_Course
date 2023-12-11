package edu.hw10;

import edu.hw10.Task1.MyObjects.MyClass;
import edu.hw10.Task1.MyObjects.MyRecord;
import edu.hw10.Task1.RandomObjectGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task1Test {
    private static final RandomObjectGenerator generator = new RandomObjectGenerator();

    @Test
    @DisplayName("RandomObjectGenerator Test : MyClass Constructor")
    void shouldGenerateRandomObjectsWithConstructor() {
        var myClass = generator.nextObject(MyClass.class);
        assertThat(myClass.getValue1()).isBetween(2, 5);
        assertThat(myClass.getValue2()).isBetween(Double.MIN_VALUE, Double.MAX_VALUE);
        assertThat(myClass.getValue3()).isNotNull();
        assertThat(myClass.isValue4()).isNotNull();
    }

    @Test
    @DisplayName("RandomObjectGenerator Test : MyClass Method")
    void shouldGenerateRandomObjectsWithMethod() {
        assertThrows(IllegalArgumentException.class, () -> generator.nextObject(MyClass.class, "error"));
        var myClass = generator.nextObject(MyClass.class, "createClass");
        assertThat(myClass.getValue2()).isLessThan(1.3);
        assertThat(myClass.getValue3()).isNotNull();
    }

    @Test
    @DisplayName("RandomObjectGenerator Test : MyRecord")
    void shouldGenerateRandomObjectsWithRecord() {
        var myRecord = generator.nextObject(MyRecord.class);
        assertThat(myRecord.value1()).isGreaterThanOrEqualTo(2);
        assertThat(myRecord.value2()).isBetween('f', 'r');
        assertThat(myRecord.value3()).isNotNull();
    }
}
