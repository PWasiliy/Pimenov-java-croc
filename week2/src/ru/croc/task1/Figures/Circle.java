package ru.croc.task1.Figures;

import ru.croc.task1.Figures.Figure;

public class Circle extends Figure {
    private int x, y, r;

    public Circle(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    @Override
    public String toString() {
        return String.format("C (%d, %d) %d", this.x, this.y, this.r);
    }
}
