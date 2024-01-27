package org.simplestore.model;


import java.util.*;

public class Inventory {
    private final Map<Integer, Product> products = Collections.synchronizedMap(new HashMap<>());

    public void addProduct(Product product) {
        synchronized (products) {
            products.put(product.getId(), product);
        }
    }

    public synchronized void removeProduct(int id) throws ProductNotFoundException {
        if (products.containsKey(id)) {
            products.remove(id);

        } else {
            throw new ProductNotFoundException("Product with ID " + id + " not found.");
        }
    }

    public synchronized Product getProduct(int id) throws ProductNotFoundException {
            Product product = products.get(id);
            if (product == null) {
                throw new ProductNotFoundException("Product with ID " + id + " not found.");
            }
            return product;

    }

    public Collection<Product> listAllProducts() {
//        for (Product product : products.values()) {
//            System.out.println("Product ID: " + product.getId() +
//                    ", Name: " + product.getName() +
//                    ", Price: " + product.getPrice());
//        }
        return products.values();
    }
}
