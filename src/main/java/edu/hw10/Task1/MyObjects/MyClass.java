package edu.hw10.Task1.MyObjects;

import edu.hw10.Task1.Annotations.Max;
import edu.hw10.Task1.Annotations.Min;
import edu.hw10.Task1.Annotations.NotNull;

public class MyClass {
    private int value1;
    private double value2;
    private String value3;
    private boolean value4;

    private MyClass(@Min(2) @Max(5) int value1, double value2, @NotNull String value3, @NotNull boolean value4) {
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
    }

    public MyClass(int value1, String value3) {
        this.value1 = value1;
        this.value3 = value3;
    }

    private MyClass(double value2, String value3) {
        this.value2 = value2;
        this.value3 = value3;
    }

    public static MyClass createClass(@Max(1.3) double value2, String value3) {
        return new MyClass(value2, value3);
    }

    public int getValue1() {
        return value1;
    }

    public double getValue2() {
        return value2;
    }

    public String getValue3() {
        return value3;
    }

    public boolean isValue4() {
        return value4;
    }
}
