package com.qikserver.grocery.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItem {
    private String productId;
    private int quantity;

}