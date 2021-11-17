package ru.croc.task13;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> comments = new ArrayList<>();
        comments.add("Первый комментарий");
        comments.add("Один два терроризм четыре пять");
        comments.add("Один два три четыре наркотики");
        comments.add("CP купил от AMD");
        comments.add("Последний комментарий");

        HashSet<String> blackList = new HashSet<>();
        blackList.add("терроризм");
        blackList.add("наркотики");
        blackList.add("cp");

        new Filter().filterComments(comments, blackList);
        for (String comment : comments) System.out.printf("%s\n", comment);
    }
}
