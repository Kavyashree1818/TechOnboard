package payment;

import interfaces.IPaymentProcessor;

public class CreditCardProcessor implements IPaymentProcessor {
    public boolean processPayment(double amount) {
        System.out.println("Processing credit card payment of $" + amount);
        return true;
    }
}
