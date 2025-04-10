package com.example.main_mobile;

public class Latihan_4_3_CartItem {
    private String name;
    private String price;
    private String stock;
    private String imageUrl;
    private int quantity;
    private double subtotal;

    public Latihan_4_3_CartItem(String name, String price, String stock, String imageUrl, int i) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.imageUrl = imageUrl;
        this.quantity = i;
        this.subtotal = Double.parseDouble(price);
    }

    // Getters
    public String getName() { return name; }
    public String getPrice() { return price; }
    public String getStock() { return stock; }
    public String getImageUrl() { return imageUrl; }
    public int getQuantity() { return quantity; }
    public double getSubtotal() { return subtotal; }

    // Setters
    public void incrementQuantity() {
        this.quantity++;
        this.subtotal = quantity * Double.parseDouble(price);
    }

    public void decrementQuantity() {
        if (this.quantity > 1) {
            this.quantity--;
            this.subtotal = quantity * Double.parseDouble(price);
        }
    }
}
