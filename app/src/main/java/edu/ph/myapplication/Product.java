package edu.ph.myapplication;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String title;
    private double price;
    private int quantity;
    private String description;
    private int imageResource;

    public Product(int id, String title, double price, int quantity, String description, int imageResource) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.imageResource = imageResource;
    }

    public Product(String title, double price, int quantity) {
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public String getDescription() { return description; }
    public int getImageResource() { return imageResource; }
    public double getSubtotal() { return price * quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}