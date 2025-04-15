package service;

import model.Customer;
import model.Order;
import model.Product;

public class InvoiceGenerator {
    public void generateInvoice(Order order, Customer customer) {
        System.out.println("\n--- Invoice ---");
        System.out.println("Customer: " + customer.getContactInfo());
        for (Product p : order.getProducts()) {
            System.out.println("- " + p.getName() + " $" + p.getPrice());
        }
        System.out.println("Total: $" + order.getTotalAmount());
    }
}
