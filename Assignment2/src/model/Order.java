package model;

import java.util.*;

public class Order {
    private List<Product> products = new ArrayList<>();
    private double totalAmount = 0;

    public void addProduct(Product product, int quantity) {  //products are added to the cart if stock is available.
        if (product.checkStock(quantity)) {
            for (int i = 0; i < quantity; i++) {
                products.add(product);
            }
            totalAmount += product.getPrice() * quantity;
            product.updateStock(quantity);
            System.out.println(quantity + " x " + product.getName() + " added to order.");
        } else {
            System.out.println("Insufficient stock for " + product.getName());
        }
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
