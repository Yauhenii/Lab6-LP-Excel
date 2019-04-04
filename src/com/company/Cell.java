package com.company;

import java.util.Objects;

public class Cell {
    private int r;
    private int c;

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public Cell(int r, int c) {
        this.r = r;
        this.c = c;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "r=" + r +
                ", c=" + c +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cell)) return false;
        Cell cell = (Cell) o;
        return getR() == cell.getR() &&
                getC() == cell.getC();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getR(), getC());
    }
}
