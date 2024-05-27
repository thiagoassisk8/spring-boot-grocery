package com.qikserver.grocery.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

}
