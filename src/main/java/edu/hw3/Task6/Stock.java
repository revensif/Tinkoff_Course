package edu.hw3.Task6;

import java.util.Objects;

public class Stock {
    private final int price;

    public Stock(int price) {
        if (price < 0) {
            this.price = 0;
            return;
        }
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    @Override public String toString() {
        return "price=" + price;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Stock stock)) {
            return false;
        }
        return price == stock.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(price);
    }
}
