package com.qikserver.grocery.entities;

import java.math.BigDecimal;

public class Order {
    private Product product;

    private int quantity;
    private BigDecimal itemTotal;
    private BigDecimal savings;

    public Order(Product product, int quantity, BigDecimal itemTotal, BigDecimal itemSavings) {
        this.product = product;
        this.quantity = quantity;
        this.itemTotal = itemTotal;
        this.savings = itemSavings;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(BigDecimal itemTotal) {
        this.itemTotal = itemTotal;
    }

    public BigDecimal getSavings() {
        return savings;
    }

    public void setSavings(BigDecimal savings) {
        this.savings = savings;
    }
}