package com.qikserver.grocery.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cupom {

    private String id;
    public CupomType type;

    private int required_qty;

    private int free_qty;
    private double price;

    private int amount;

}