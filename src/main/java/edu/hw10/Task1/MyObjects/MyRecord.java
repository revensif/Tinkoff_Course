package edu.hw10.Task1.MyObjects;

import edu.hw10.Task1.Annotations.Max;
import edu.hw10.Task1.Annotations.Min;
import edu.hw10.Task1.Annotations.NotNull;

public record MyRecord(@Min(2.0) float value1, @Min('f') @Max('r') char value2, @NotNull Integer value3) {
}
