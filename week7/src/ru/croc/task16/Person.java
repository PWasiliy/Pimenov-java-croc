package ru.croc.task16;

public class Person {
    private final String name;
    private final int age;
    public Person (String name, int age) {
        this.name = name;
        this.age = age;
    }
    public Person (String nameAndAge) {
        this(nameAndAge.split(",")[0], Integer.parseInt(nameAndAge.split(",")[1]));
    }
    public int getAge() {
        return this.age;
    }
    public String getName() {
        return this.name;
    }
}