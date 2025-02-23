package com.example.clothaplication;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private String imageUrl;
    private int price;

    public Product(String name, String imageUrl, int price) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getPrice() {
        return price;
    }
}
