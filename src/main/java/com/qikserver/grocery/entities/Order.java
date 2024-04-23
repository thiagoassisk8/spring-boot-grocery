package com.qikserver.grocery.entities;

public class Order {
    private Product product;

    private int quantity;
    private double itemTotal;
    private double savings;

    public Order(Product product, int quantity, double itemTotal, double savings) {
        this.product = product;
        this.quantity = quantity;
        this.itemTotal = itemTotal;
        this.savings = savings;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(double itemTotal) {
        this.itemTotal = itemTotal;
    }

    public double getSavings() {
        return savings;
    }

    public void setSavings(double savings) {
        this.savings = savings;
    }
}
