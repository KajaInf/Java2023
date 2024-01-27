package org.simplestore.model;

public class Product {
    private final int id;
    private final String name;
    private final double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }


// Getters and toString() method, see: src/test/java/org/simplestore/model/ProductTest.java
    // TODO: Implement getters for id, name, and price

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    // TODO: Override toString() method for Product representation
@Override
    public String toString() {

        return "Product{id=" + id + ", name='" + name + "', price=" + price + '}';
    }

    public int getId() {
        return id;
    }
}

