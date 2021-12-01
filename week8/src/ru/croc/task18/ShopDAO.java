package ru.croc.task18;

import java.sql.*;
import java.util.List;

public class ShopDAO {
    private final String SQL_EXCEPTION = "Произошла ошибка при работе с БД, текст ошибки: %s\n";
    private final Connection connection;
    public ShopDAO(Connection connection) {
        this.connection = connection;
    }

    public Product findProduct(String productCode) {
        Product product = null;
        try (PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM products p WHERE p.article = ?")) {
            statement.setString(1, productCode);
            if (statement.execute())
                try (ResultSet resultSet = statement.getResultSet()) {
                     if (resultSet.first())
                        product = new Product(resultSet.getString("article"), resultSet.getString("name"), resultSet.getInt("price"));
                }
        } catch (SQLException e) {
            System.out.printf(SQL_EXCEPTION, e.getMessage());
        }
        return product;
    }

    public Product createProduct(Product product) throws SQLException {
        if (this.findProduct(product.article) != null)
            throw new SQLException(String.format("Товар с артикулом \"%s\" уже существует в БД.", product.article));

        try (PreparedStatement statement = this.connection.prepareStatement("INSERT INTO products (article, name, price) VALUES (?, ?, ?)")) {
            statement.setString(1, product.article);
            statement.setString(2, product.name);
            statement.setInt(3, product.price);

            statement.executeUpdate();
        }
        return product;
    }

    public Product updateProduct(Product product) throws SQLException {
        if (this.findProduct(product.article) == null)
            throw new SQLException(String.format("В БД не удалось найти товар с артикулом \"%s\".\n", product.article));

        try (PreparedStatement statement = this.connection.prepareStatement("UPDATE products p SET p.name = ?, p.price = ? WHERE p.article = ?")) {
            statement.setString(1, product.name);
            statement.setInt(2, product.price);
            statement.setString(3, product.article);

            statement.executeUpdate();
        }
        return product;
    }

    public void deleteProduct(String productArticle) {
        try (PreparedStatement statement = this.connection.prepareStatement("DELETE FROM products p WHERE p.article = ?")) {
            statement.setString(1, productArticle);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.printf(SQL_EXCEPTION, e.getMessage());
        }
    }

    public Order createOrder(String userLogin, List<Product> products) {
        for (Product product : products)
            if (this.findProduct(product.article) == null)
                return null;

        try (PreparedStatement statement = this.connection.prepareStatement("INSERT INTO orders (user_login) VALUES (?)")) {
            statement.setString(1, userLogin);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.printf(SQL_EXCEPTION, e.getMessage());
            return null;
        }

        int id = -1;
        try (Statement statement = this.connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery("SELECT MAX(id) as max_id FROM orders")) {
                if (resultSet.first())
                    id = resultSet.getInt("max_id");
            }
        } catch (SQLException e) {
            System.out.printf(SQL_EXCEPTION, e.getMessage());
        }

        if (id < 0)
            return null;

        try (PreparedStatement statement = this.connection.prepareStatement("INSERT INTO orders_items (order_id, item_num, product_article) VALUES (?, ?, ?)")) {
            for (int i = 0; i < products.size(); i++) {
                statement.setInt(1, id);
                statement.setInt(2, i + 1);
                statement.setString(3, products.get(i).article);

                statement.executeUpdate();
            }

            Order order = new Order(id, userLogin);
            order.getProducts().addAll(products);
            return order;

        } catch (SQLException e) {
            System.out.printf(SQL_EXCEPTION, e.getMessage());
            return null;
        }
    }
}
