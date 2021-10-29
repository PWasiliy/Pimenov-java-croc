package ru.croc.task1.Figures;

import ru.croc.task2.Movable;

public class Circle extends Figure implements Movable {
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

    @Override
    public boolean isPointIn(int x, int y) {
        double dist = Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2));
        return dist <= this.r;
    }

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
}
