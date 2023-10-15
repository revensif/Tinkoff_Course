package edu.hw2;

public final class Task2 {

    private Task2() {
    }

    public static class Rectangle {
        private final int width;
        private final int height;

        public Rectangle() {
            width = 0;
            height = 0;
        }

        public Rectangle(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public Rectangle setWidth(int width) {
            return new Rectangle(width, height);
        }

        public Rectangle setHeight(int height) {
            return new Rectangle(width, height);
        }

        public double area() {
            return width * height;
        }
    }

    public static class Square extends Rectangle {

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
}
