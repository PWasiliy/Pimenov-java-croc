package ru.croc.task16;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        ArrayDeque<AgeGroup> groups =  new ArrayDeque<>(args.length);
        for (int i = 0; i < args.length; i++) {
            int age = Integer.parseInt(args[i]);
            if (i == 0)
                groups.addLast(new AgeGroup(0, age));
            else if (i == args.length - 1)
                groups.addLast(new AgeGroup(age + 1, Integer.MAX_VALUE));
            else
                groups.addLast(new AgeGroup(groups.getLast().getMax() + 1, age));
        }
        System.out.printf("Количество возрастных групп: %d\n", groups.size());

        System.out.println("Введите данные респондентов:");
        ArrayList<Person> people = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while(!input.equals("END")) {
            people.add(new Person(input));
            input = scanner.nextLine();
        }
        System.out.printf("Количество респондентов: %d\n", people.size());

        for (Person person : people) {
            for (AgeGroup group : groups) {
                if (group.getMin() <= person.getAge() && person.getAge() <= group.getMax()) {
                    group.people.add(person);
                    break;
                }
            }
        }

        groups.removeIf((AgeGroup group) -> group.people.size() == 0);
        groups.iterator().forEachRemaining((AgeGroup group) -> group.people.sort((Person left, Person right) ->
            {int result = Integer.compare(left.getAge(), right.getAge()) * -1;
            return result != 0 ? result : left.getName().compareTo(right.getName());}));

        for (int i = groups.size() - 1; i >= 0; i--)
            System.out.printf("%s\n", groups.pollLast());
    }
}
