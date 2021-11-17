package ru.croc.task14;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final String USERS_PATH = System.getProperty("user.dir") + "\\src\\ru\\croc\\task14\\Users.txt";
    private static final String FILMS_PATH = System.getProperty("user.dir") + "\\src\\ru\\croc\\task14\\Films.txt";

    public static void main(String[] args) {
        Scanner usersScanner = null;
        Scanner filmsScanner = null;
        try {
            usersScanner = new Scanner(Paths.get(USERS_PATH));
            filmsScanner = new Scanner(Paths.get(FILMS_PATH));
        } catch (IOException e) {
            System.out.printf("Не удалось открыть файл, ошибка: %s", e.getMessage());
            return;
        }

        // заполняем список всех пользователей и их просмотров
        UserList allUsers = new UserList();
        while (usersScanner.hasNextLine()) allUsers.add(new User(usersScanner.nextLine()));

        // заполняем список всех фильмов
        ArrayList<Film> allFilms = new ArrayList<>();
        while (filmsScanner.hasNextLine()) {
            Film film = new Film(filmsScanner.nextLine());
            film.views = allUsers.getViewsCount(film.getId());
            allFilms.add(film);
        }

        User inputUser = new User(args[0]);
        UserList users = new UserList();
        for (User user : allUsers)
            if (user.getOverlapsCount(inputUser) >= inputUser.getHistory().size() / 2.0)
                users.add(user);

        allFilms.removeIf((Film film) -> inputUser.getHistory().contains(film.getId()) || users.getViewsCount(film.getId()) == 0);
        allFilms.sort((left, right) -> Integer.compare(left.views, right.views) * -1);
        System.out.printf("%s\n", allFilms.size() > 0 ? allFilms.get(0).getName() : "Не удалось подобрать фильм.");
    }
}
