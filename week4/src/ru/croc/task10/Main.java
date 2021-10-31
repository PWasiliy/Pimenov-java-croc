package ru.croc.task10;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static class Call {
        private int since, till;
        public Call(int since, int till) {
            this.since = since;
            this.till = till;
        }
        public boolean isBetween(Call call) {
            return ((this.since <= call.till) && (this.till >= call.since));
        }
        public int betweenCount(Call[] calls) {
            int count = 0;
            for (Call call : calls) {
                count = this.isBetween(call) ? ++count : count;
            }
            return count;
        }
    }

    public static void main(String[] args) {
        System.out.printf("Выполняется подсчет пикового количества звонков по файлу логов \"%s\"\n", args[0]);
        Scanner scanner = null;
        try {
            scanner = new Scanner(Paths.get(args[0]));
        } catch (IOException e) {
            System.out.printf("Не удалось открыть файл, ошибка: %s\n", e.getMessage());
        }

        List<Call> calls = new ArrayList<Call>();
        while (scanner.hasNextLine()) {
            String[] arr = scanner.nextLine().split(",");
            calls.add(new Call(Integer.parseInt(arr[0]), Integer.parseInt(arr[1])));
        }

        int max = 0;
        for (Call call : calls) {
            int count = call.betweenCount(calls.toArray(new Call[0])) - 1;
            max = Math.max(max, count);
        }

        System.out.printf("Пиковое количество звонков: %d", max);
    }
}
