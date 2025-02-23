package com.example.clothaplication;

public class PaymentProcessor {

    // Simulate a payment process
    public static boolean processPayment(double totalAmount, String paymentMethod) {
        // Simulate successful payment
        if (paymentMethod.equals("credit_card")) {
            return true;  // Simulate successful payment
        }
        return false;  // Payment failure (for other methods or issues)
    }
}