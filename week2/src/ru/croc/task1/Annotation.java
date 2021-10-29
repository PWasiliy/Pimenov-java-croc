package ru.croc.task1;

import ru.croc.task1.Figures.Figure;

public class Annotation {
    private Figure figure;
    private String caption;

    public Annotation(Figure figure, String caption) {
        this.figure = figure;
        this.caption = caption;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", this.figure.toString(), caption);
    }
}
