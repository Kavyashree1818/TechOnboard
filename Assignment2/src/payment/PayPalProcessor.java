package payment;

import interfaces.IPaymentProcessor;

public class PayPalProcessor implements IPaymentProcessor {
    public boolean processPayment(double amount) {
        System.out.println("Processing PayPal payment of $" + amount);
        return true;
    }
}
