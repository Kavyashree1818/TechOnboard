package model;


import interfaces.IStockManager;

public class Product implements IStockManager {
    private String name;
    private double price;
    private int stock;

    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getDetails() { //returns product details
        return name + " - Price: $" + price + ", Stock: " + stock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean checkStock(int quantity) { // check if needed stock is available
        return stock >= quantity;
    }

    @Override
    public void updateStock(int quantity) { // deduct the stock after buying
        if (stock >= quantity) {
            stock -= quantity;
        }
    }
}
