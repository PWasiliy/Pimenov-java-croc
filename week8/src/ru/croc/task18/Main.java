package ru.croc.task18;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Не удалось импортировать класс H2-драйвера.\n");
            return;
        }

        try (Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "")) {
            ShopDAO shopDAO = new ShopDAO(connection);

            System.out.println("Введите команды:");
            Scanner scanner = new Scanner(System.in);
            mainLoop :
            while (true) {
                String[] strings = scanner.nextLine().split(" ");
                if (strings.length == 0) {
                    System.out.println("Не была введена команда.");
                    continue;
                }

                switch (strings[0]) {
                    case ("ТОВАР") :
                        if (shopDAO.findProduct(strings[1]) != null)
                            System.out.printf("Товар с артикулом \"%s\" уже существует в БД.\n", strings[1]);
                        else
                            shopDAO.createProduct(new Product(strings[1], strings[2], Integer.parseInt(strings[3])));
                        break;

                    case ("ИЗМЕНИТЬ") :
                        if (shopDAO.findProduct(strings[1]) == null)
                            System.out.printf("В БД не удалось найти товар с артикулом \"%s\".\n", strings[1]);
                        else
                            shopDAO.updateProduct(new Product(strings[1], strings[2], Integer.parseInt(strings[3])));
                        break;

                    case ("УДАЛИТЬ") :
                        shopDAO.deleteProduct(strings[1]);
                        break;

                    case ("ЗАКАЗ") :
                        ArrayList<Product> products = new ArrayList<>(strings.length - 2);
                        for (int i = 2; i < strings.length; i++) {
                            Product product = shopDAO.findProduct(strings[i]);
                            if (product == null) {
                                System.out.printf("В БД не удалось найти товар с артикулом \"%s\".\n", strings[i]);
                                continue mainLoop;
                            } else
                                products.add(product);
                        }

                        shopDAO.createOrder(strings[1], products);
                        break;

                    case ("КОНЕЦ") :
                        break mainLoop;

                    default: System.out.println("Неизвестная команда, попробуйте ещё раз.");
                }
            }

        } catch (SQLException e) {
            System.out.printf("Произошла ошибка при работе с БД, текст ошибки: %s\n", e.getMessage());
            return;
        }
    }
}
