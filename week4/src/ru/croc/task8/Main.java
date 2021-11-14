package ru.croc.task8;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        System.out.printf("Выполняется подсчет количества слов в файле \"%s\"\n", args[0]);
        Scanner scanner = null;
        try {
            scanner = new Scanner(Paths.get(args[0]));
        } catch (IOException e) {
            System.out.printf("Не удалось открыть указанный файл, ошибка: \"%s\"", e.getMessage());
            return;
        }

        Pattern pattern = Pattern.compile("[a-zа-яA-ZА-Я_0-9]+");
        int count = 0;
        while (scanner.hasNextLine()) {
            Matcher matcher = pattern.matcher(scanner.nextLine());
            while (matcher.find()) ++count;
        }
        System.out.printf("Количество слов в файле: %d", count);
    }
}
