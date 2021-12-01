package ru.croc.task17;

import ru.croc.task18.Order;
import ru.croc.task18.Product;
import ru.croc.task18.ShopDAO;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static void prepareDB(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            boolean hasProducts = false;
            boolean hasOrders = false;
            boolean hasOrdersItems = false;
            DatabaseMetaData metaData = connection.getMetaData();
            try (ResultSet resultSet = metaData.getTables(null, null, null, null)) {
                while (resultSet.next()) {
                    String tableName = resultSet.getString("TABLE_NAME");
                    hasProducts = hasProducts || tableName.equalsIgnoreCase("products");
                    hasOrders = hasOrders || tableName.equalsIgnoreCase("orders");
                    hasOrdersItems = hasOrdersItems || tableName.equalsIgnoreCase("orders_items");
                }

                if (!(hasProducts && hasOrders && hasOrdersItems)) {
                    if (!hasProducts)
                        statement.execute(String.format("CREATE TABLE products (id INT PRIMARY KEY AUTO_INCREMENT, article VARCHAR(255) UNIQUE, name VARCHAR(255), price INT)"));
                    if (!hasOrders)
                        statement.execute(String.format("CREATE TABLE orders (id INT PRIMARY KEY AUTO_INCREMENT, user_login VARCHAR(255))"));
                    if (!hasOrdersItems)
                        statement.execute(String.format("CREATE TABLE orders_items (id INT PRIMARY KEY AUTO_INCREMENT, order_id INT, item_num INT, product_article VARCHAR(255), " +
                                "CONSTRAINT FK_order_id_in_items FOREIGN KEY (order_id) REFERENCES orders(id), CONSTRAINT FK_product_article_in_items FOREIGN KEY (product_article) REFERENCES products(article))"));

                    connection.commit();
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(Paths.get(args[0]));
        } catch (IOException e) {
            System.out.printf("Не удалось открыть CSV-файл с заказами, ошибка: %s\n", e.getMessage());
            return;
        }

        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Не удалось импортировать класс H2-драйвера.\n");
            return;
        }

        try (Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "")) {
            prepareDB(connection);
            ShopDAO shopDAO = new ShopDAO(connection);

            ArrayList<Order> orders = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String[] strings = scanner.nextLine().split(",");
                int orderId = Integer.parseInt(strings[0]);
                Order order = null;
                for (Order tempOrder : orders)
                    if (tempOrder.id == orderId) {
                        order = tempOrder;
                        break;
                    }

                if (order == null) {
                    order = new Order(orderId, strings[1]);
                    orders.add(order);
                }

                Product product = null;
                product = shopDAO.findProduct(strings[2]);
                if (product == null) {
                    product = new Product(strings[2], strings[3], Integer.parseInt(strings[4]));
                    shopDAO.createProduct(product);
                }

                order.getProducts().add(product);
            }

            orders.iterator().forEachRemaining((Order order) -> shopDAO.createOrder(order.userLogin, order.getProducts()));

        } catch (SQLException e) {
            System.out.printf("Произошла ошибка при работе с БД, текст ошибки: %s\n", e.getMessage());
        }
    }
}
