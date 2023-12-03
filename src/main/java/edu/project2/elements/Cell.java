package edu.project2.elements;

import java.util.Objects;

public class Cell {
    private final int x;
    private int y;
    boolean rightWall;
    boolean downWall;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        rightWall = false;
        downWall = false;
    }

    public Cell(int x, int y, boolean rightWall, boolean downWall) {
        this.x = x;
        this.y = y;
        this.rightWall = rightWall;
        this.downWall = downWall;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isRightWall() {
        return rightWall;
    }

    public void setRightWall(boolean rightWall) {
        this.rightWall = rightWall;
    }

    public boolean isDownWall() {
        return downWall;
    }

    public void setDownWall(boolean downWall) {
        this.downWall = downWall;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cell cell = (Cell) o;
        return x == cell.x && y == cell.y && rightWall == cell.rightWall && downWall == cell.downWall;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, rightWall, downWall);
    }
}


