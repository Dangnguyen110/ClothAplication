package com.example.clothaplication;

import java.util.ArrayList;

public class Cart {
    private ArrayList<Product> cart;

    public Cart() {
        cart = new ArrayList<>();
    }

    public void addToCart(Product product) {
        cart.add(product);
    }

    public ArrayList<Product> getCart() {
        return cart;
    }

    public int calculateTotal() {
        int total = 0;
        for (Product product : cart) {
            total += product.getPrice();
        }
        return total;
    }

    public void clearCart() {
        cart.clear();
    }
}


