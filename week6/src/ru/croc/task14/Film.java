package ru.croc.task14;

import java.util.Objects;

public class Film {
    private int id;
    private String name;
    public int views;

    public Film(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Film(String idAndName) {
        this(Integer.parseInt(idAndName.split(",")[0]), idAndName.split(",")[1]);
    }
    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return id == film.id && views == film.views && Objects.equals(name, film.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, views);
    }
}
