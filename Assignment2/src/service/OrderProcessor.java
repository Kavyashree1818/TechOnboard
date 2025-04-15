package service;

import interfaces.IPaymentProcessor;
import model.Customer;
import model.Order;

public class OrderProcessor {
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
