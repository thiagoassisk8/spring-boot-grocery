package com.qikserver.grocery.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cupom {

    private String id;
    public CupomType type;

    @JsonProperty("required_qty")
    private int requiredQty;

    @JsonProperty("free_qty")
    private int freeQty;
    private double price;

    private int quantity;

    public Cupom(String id, CupomType type, int requiredQty, int freeQty, double price, int quantity) {
        this.id = id;
        this.type = type;
        this.requiredQty = requiredQty;
        this.freeQty = freeQty;
        this.price = price;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CupomType getType() {
        return type;
    }

    public void setType(CupomType type) {
        this.type = type;
    }

    public int getRequiredQty() {
        return requiredQty;
    }

    public void setRequiredQty(int requiredQty) {
        this.requiredQty = requiredQty;
    }

    public int getFreeQty() {
        return freeQty;
    }

    public void setFreeQty(int freeQty) {
        this.freeQty = freeQty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}