package ru.croc.task18;

import java.util.ArrayList;
import java.util.List;

public class Order {
    public final int id;
    public final String userLogin;
    private ArrayList<Product> products = new ArrayList<>();

    public Order (int id, String userLogin) {
        this.id = id;
        this.userLogin = userLogin;
    }
    public List<Product> getProducts() {
        return this.products;
    }
}
