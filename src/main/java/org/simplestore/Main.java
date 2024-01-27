package org.simplestore;

import org.simplestore.model.Inventory;
import org.simplestore.model.Product;
import org.simplestore.model.ProductNotFoundException;
import org.simplestore.service.ShoppingCart;
import org.simplestore.util.InventoryLoader;

public class Main {
    public static void main(String[] args) throws ProductNotFoundException {
        Inventory inventory = new Inventory();
        InventoryLoader.loadInventory("E:\\java reference\\lab06kaja\\src\\main\\resources/inventory.txt", inventory);

        ShoppingCart shoppingCart = new ShoppingCart(inventory);

        try {
            System.out.println("Adding products to the shopping cart...");
            shoppingCart.addItem(1, 2);
            shoppingCart.addItem(2, 3);

            double totalPrice = shoppingCart.calculateTotalCost();
            System.out.println("Total price of the cart: " + totalPrice);

            System.out.println("Clearing the shopping cart...");
            shoppingCart.clearCart();

            totalPrice = shoppingCart.calculateTotalCost();
            System.out.println("Total price of the cart: " + totalPrice);

            System.out.println("Adding a new product to the inventory...");
            Product newProduct = new Product(11, "Pizza", 5.99);
            inventory.addProduct(newProduct);

            System.out.println(inventory.listAllProducts());


            System.out.println("Removing a product from the inventory...");
            inventory.removeProduct(1);

            System.out.println(inventory.listAllProducts());



        } catch (ProductNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}
