package ru.croc.task19;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try (FileWriter fileWriter = new FileWriter("HelloWorld.txt", true)) {
            fileWriter.write("Hello, world!\n");
            fileWriter.flush();
        } catch (IOException e) {
            System.out.printf("Произошла ошибка при работе с файлом, текст ошибки: %s", e.getMessage());
        }
    }
}
