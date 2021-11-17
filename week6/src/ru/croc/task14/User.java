package ru.croc.task14;

import java.util.ArrayList;
import java.util.Objects;

public class User {
    private ArrayList<Integer> history = new ArrayList<>();

    public User(String viewsHistory) {
        String[] filmsID = viewsHistory.split(",");
        for (String filmID : filmsID) this.history.add(Integer.parseInt(filmID));
    }
    public ArrayList<Integer> getHistory() {
        return this.history;
    }
    public int getOverlapsCount(User user) {
        ArrayList<Integer> overlaps = new ArrayList<>();
        for (int filmID : this.history)
            if (user.history.contains(filmID) && !overlaps.contains(filmID))
                overlaps.add(filmID);

        return overlaps.size();
    }
    public int getViewsCount(int filmId) {
        int count = 0;
        for (int i = 0; i < this.history.size(); i++)
            count = this.history.get(i) == filmId ? ++count : count;

        return count;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(history, user.history);
    }
    @Override
    public int hashCode() {
        return Objects.hash(history);
    }
}