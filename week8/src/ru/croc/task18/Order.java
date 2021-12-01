package ru.croc.task18;

import java.util.ArrayList;
import java.util.List;

public class Order {
    public final int id;
    private ArrayList<Product> products = new ArrayList<>();

    public Order (int id) {
        this.id = id;
    }
    public List<Product> getProducts() {
        return this.products;
    }
}
