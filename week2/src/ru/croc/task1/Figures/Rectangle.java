package ru.croc.task1.Figures;

import ru.croc.task2.Movable;

public class Rectangle extends Figure implements Movable  {
    private int x1, y1, x2, y2;

    public Rectangle(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public String toString() {
        return String.format("R (%d, %d), (%d, %d)", this.x1, this.y1, this.x2, this.y2);
    }

    @Override
    public boolean isPointIn(int x, int y) {
        return (x >= this.x1) && (x <= this.x2) && (y >= this.y1) && (y <= this.y2);
    }

    public void move(int dx, int dy) {
        this.x1 += dx;
        this.y1 += dy;
        this.x2 += dx;
        this.y2 += dy;
    }
}
