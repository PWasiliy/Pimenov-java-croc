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
        public boolean isBetween(int moment) {
            return (this.since <= moment) && (moment <= this.till);
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

        int since = Integer.MAX_VALUE;
        int till = Integer.MIN_VALUE;
        List<Call> calls = new ArrayList<Call>();
        while (scanner.hasNextLine()) {
            String[] arr = scanner.nextLine().split(",");
            Call call = new Call(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
            calls.add(call);

            since = Math.min(since, call.since);
            till = Math.max(till, call.till);
        }

        int max = 0;
        for (int i = since; i <= till; i++) {
            int count = 0;
            for (Call call : calls)
                if (call.isBetween(i))
                    count++;

            max = Math.max(max, count);
        }

        System.out.printf("Пиковое количество звонков: %d\n", max);
    }
}
