package edu.hw2.Task2;

public class Square extends Rectangle {

    public Square() {
    }

    @Override
    public Rectangle setWidth(int width) {
        return super.setWidth(width).setHeight(width);
    }

    @Override
    public Rectangle setHeight(int height) {
        return super.setWidth(height).setHeight(height);
    }
}
