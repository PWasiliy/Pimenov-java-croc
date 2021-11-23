package ru.croc.task16;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        ArrayList<AgeGroup> groups =  new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            Short age = Short.parseShort(args[i]);
            if (i == 0)
                groups.add(new AgeGroup(0, age));
            else if (i == args.length - 1)
                groups.add(new AgeGroup(age, Short.MAX_VALUE));
            else
                groups.add(new AgeGroup(groups.get(groups.size() - 1).getMax() + 1, age));
        }

        System.out.println("Введите данные респондентов:");
        ArrayList<Person> people = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while(!input.equals("END")) {
            people.add(new Person(input));
            input = scanner.nextLine();
        }
        System.out.printf("Количество респондентов: %d\n", people.size());
    }
}
