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

    private int amount;

    public Cupom(String id, CupomType type, int requiredQty, int freeQty, double price, int amount) {
        this.id = id;
        this.type = type;
        this.requiredQty = requiredQty;
        this.freeQty = freeQty;
        this.price = price;
        this.amount = amount;
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

    public int getAmount() {
        return amount;
    }

    public int setAmount(int amount) {
        return this.amount = amount;
    }
}