import java.util.*;

// Interfaces for ISP and DIP
interface IPaymentProcessor {
    boolean processPayment(double amount);
}

interface IStockManager {
    boolean checkStock(int quantity);
    void updateStock(int quantity);
}

// Product class (SRP + ISP)
class Product implements IStockManager {
    private String name;
    private double price;
    private int stock;

    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getDetails() {
        return name + " - Price: $" + price + ", Stock: " + stock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean checkStock(int quantity) {
        return stock >= quantity;
    }

    @Override
    public void updateStock(int quantity) {
        if (stock >= quantity) {
            stock -= quantity;
        }
    }
}

// Customer class (SRP)
class Customer {
    private String name;
    private String email;
    private String address;

    public Customer(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public String getContactInfo() {
        return name + ", " + email + ", " + address;
    }
}

// Order class (SRP)
class Order {
    private List<Product> products = new ArrayList<>();
    private double totalAmount = 0;

    public void addProduct(Product product, int quantity) {
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

// Payment Processors (OCP + LSP)
class CreditCardProcessor implements IPaymentProcessor {
    public boolean processPayment(double amount) {
        System.out.println("Processing credit card payment of $" + amount);
        return true; // assume payment success
    }
}

class PayPalProcessor implements IPaymentProcessor {
    public boolean processPayment(double amount) {
        System.out.println("Processing PayPal payment of $" + amount);
        return true; // assume payment success
    }
}

// Invoice Generator (SRP)
class InvoiceGenerator {
    public void generateInvoice(Order order, Customer customer) {
        System.out.println("\n--- Invoice ---");
        System.out.println("Customer: " + customer.getContactInfo());
        for (Product p : order.getProducts()) {
            System.out.println("- " + p.getName() + " $" + p.getPrice());
        }
        System.out.println("Total: $" + order.getTotalAmount());
    }
}

// Order Processor (OCP, DIP)
class OrderProcessor {
    private IPaymentProcessor paymentProcessor;
    private InvoiceGenerator invoiceGenerator;

    public OrderProcessor(IPaymentProcessor paymentProcessor, InvoiceGenerator invoiceGenerator) {
        this.paymentProcessor = paymentProcessor;
        this.invoiceGenerator = invoiceGenerator;
    }

    public void processOrder(Order order, Customer customer) {
        if (paymentProcessor.processPayment(order.getTotalAmount())) {
            System.out.println("Payment successful!");
            invoiceGenerator.generateInvoice(order, customer);
        } else {
            System.out.println("Payment failed.");
        }
    }
}

// Main Class - User Interaction
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Sample products
        List<Product> inventory = new ArrayList<>();
        inventory.add(new Product("Laptop", 1500, 10));
        inventory.add(new Product("Smartphone", 800, 5));
        inventory.add(new Product("Headphones", 150, 20));

        // Display products
        System.out.println("Available Products:");
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println((i + 1) + ". " + inventory.get(i).getDetails());
        }

        // Customer Info
        System.out.print("\nEnter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        Customer customer = new Customer(name, email, address);

        Order order = new Order();

        // Add products to order
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

        // Select payment type
        scanner.nextLine(); // consume newline
        System.out.print("\nChoose payment method (credit/paypal): ");
        String paymentType = scanner.nextLine().toLowerCase();

        IPaymentProcessor processor;
        if (paymentType.equals("credit")) {
            processor = new CreditCardProcessor();
        } else {
            processor = new PayPalProcessor();
        }

        // Process order
        OrderProcessor orderProcessor = new OrderProcessor(processor, new InvoiceGenerator());
        orderProcessor.processOrder(order, customer);

        scanner.close();
    }
}

