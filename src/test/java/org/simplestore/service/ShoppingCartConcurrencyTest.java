package org.simplestore.service;

import org.junit.jupiter.api.Test;
import org.simplestore.model.Inventory;
import org.simplestore.model.Product;
import org.simplestore.model.ProductNotFoundException;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShoppingCartConcurrencyTest {
    private final Inventory inventory = new Inventory();

    @Test
    void addAndRemoveItemsConcurrently() throws InterruptedException {
        ShoppingCart shoppingCart = new ShoppingCart(inventory);
        inventory.addProduct(new Product(1, "Test Product", 10.0));

        Runnable addTask = () -> shoppingCart.addItem(1, 10); // Each thread adds 10 items
        Runnable removeTask = () -> {
            try {
                shoppingCart.removeItem(1, 5); // Each thread removes 5 items
            } catch (ProductNotFoundException e) {
                e.printStackTrace();
            }
        };

        Thread[] addThreads = new Thread[10]; // 10 threads adding 10 items each = 100 items added
        Thread[] removeThreads = new Thread[10]; // 10 threads removing 5 items each = 50 items removed

        for (int i = 0; i < 10; i++) {
            addThreads[i] = new Thread(addTask);
            removeThreads[i] = new Thread(removeTask);
            addThreads[i].start();
        }

        for (Thread thread : addThreads) thread.join();
        for (int i = 0; i < 10; i++) removeThreads[i].start();
        for (Thread thread : removeThreads) thread.join();

        assertEquals(50, shoppingCart.getItemQuantity(1));
    }



    @Test
    void calculateTotalCostConcurrently() throws InterruptedException, ProductNotFoundException {
        ShoppingCart shoppingCart = new ShoppingCart(inventory);
        inventory.addProduct(new Product(1, "Test Product", 10.0));

        Runnable addTask = () -> shoppingCart.addItem(1, 10); // Each thread adds 10 items

        Thread[] threads = new Thread[10]; // 10 threads adding 10 items each = 100 items added

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(addTask);
            threads[i].start();
        }

        for (Thread thread : threads) thread.join();

        assertEquals(1000.0, shoppingCart.calculateTotalCost());    }


    // Note for presenter: Discuss the importance of concurrency testing in a multi-threaded environment.
}
