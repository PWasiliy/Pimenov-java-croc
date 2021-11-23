package ru.croc.task16;

public class Person {
    private String name;
    private short age;
    public Person (String name, short age) {
        this.name = name;
        this.age = age;
    }
    public Person (String nameAndAge) {
        this(nameAndAge.split(",")[0], Byte.parseByte(nameAndAge.split(",")[1]));
    }
}