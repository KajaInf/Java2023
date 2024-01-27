package org.simplestore.service;

import org.simplestore.model.Inventory;
import org.simplestore.model.Product;
import org.simplestore.model.ProductNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private final Inventory inventory;
    private final Map<Integer, Integer> cartItems = new HashMap<>();

    public synchronized void addItem(int productId, int quantity) {
        cartItems.merge(productId, quantity, Integer::sum);  // Equivalent of lambda (a, b) -> Integer.sum(a, b)
    }

    // See file: src/test/java/org/simplestore/service/ShoppingCartTest.java
    public synchronized int getItemQuantity(int productId) {
        return cartItems.getOrDefault(productId, 0);
    }

    // TODO: Implement a method to remove a product from the cart
    public synchronized void removeItem(int productId, int quantity) throws ProductNotFoundException {

            if (!cartItems.containsKey(productId)) {
                throw new ProductNotFoundException("Product with ID " + productId + " not found in cart.");
            }else {
                int current = cartItems.get(productId);

                if (current > quantity) {
                    cartItems.put(productId, current - quantity);
                } else if (current== quantity) {
                    cartItems.remove(productId);
                } else {
                    throw new ProductNotFoundException("Attempted to remove more items than available in the cart for product with ID " + productId);
                }
            }

    }

    // TODO: Implement a method to calculate the total price of the cart
    public double calculateTotalCost() throws ProductNotFoundException {
        synchronized (cartItems) {
            double total = 0.0;
            for (Map.Entry<Integer, Integer> entry : cartItems.entrySet()) {
                Product product = inventory.getProduct(entry.getKey()); // This might throw ProductNotFoundException
                total += product.getPrice() * entry.getValue();
            }
            return total;
        }
    }
    // TODO: Implement a method to clear the cart
    public void clearCart() {
        synchronized (cartItems) {
            cartItems.clear();
        }
    }


    public ShoppingCart(Inventory inventory) {
        this.inventory = inventory;
    }
}
