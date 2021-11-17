package ru.croc.task14;

import java.util.ArrayList;

public class UserList extends ArrayList<User> {
    public int getViewsCount(int filmId) {
        int count = 0;
        for (User user : this)
            count += user.getViewsCount(filmId);

        return count;
    }
}
