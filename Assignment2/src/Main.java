import model.*;
import payment.*;
import service.*;
import interfaces.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Product> inventory = new ArrayList<>(); // Array list for storing products
        inventory.add(new Product("Laptop", 1500, 10));
        inventory.add(new Product("Smartphone", 800, 5));
        inventory.add(new Product("Headphones", 150, 20));

        System.out.println("Available Products:"); // loop to print list of available products
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println((i + 1) + ". " + inventory.get(i).getDetails());
        }

        System.out.print("\nEnter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        Customer customer = new Customer(name, email, address);

        Order order = new Order();

        while (true) {
            System.out.print("\nEnter product number to add (0 to finish): ");
            int choice = scanner.nextInt();
            if (choice == 0) break;
            System.out.print("Enter quantity: ");
            int qty = scanner.nextInt();
            if (choice > 0 && choice <= inventory.size()) {
                order.addProduct(inventory.get(choice - 1), qty);
            } else {
                System.out.println("Invalid product number.");
            }
        }

        if (order.getProducts().isEmpty()) {
            System.out.println("No products selected. Exiting...");
            return;
        }

        scanner.nextLine(); 
        System.out.print("\nChoose payment method (credit/paypal): ");
        String paymentType = scanner.nextLine().toLowerCase();

        IPaymentProcessor processor = paymentType.equals("credit") 
                ? new CreditCardProcessor()
                : new PayPalProcessor(); // condition for payment methods

        OrderProcessor orderProcessor = new OrderProcessor(processor, new InvoiceGenerator());
        orderProcessor.processOrder(order, customer);

        scanner.close();
    }
}
