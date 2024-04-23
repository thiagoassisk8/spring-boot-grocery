package com.qikserver.grocery.entities;

public class Cupom {

    private String id;
    public CupomType type;

    private int required_qty;

    private int free_qty;
    private double price;

    private int amount;

    public Cupom(String id, CupomType type, int required_qty, int free_qty, double price, int amount) {
        this.id = id;
        this.type = type;
        this.required_qty = required_qty;
        this.free_qty = free_qty;
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
        return required_qty;
    }

    public void setRequiredQty(int required_qty) {
        this.required_qty = required_qty;
    }

    public int getFreeQty() {
        return free_qty;
    }

    public void setFreeQty(int free_qty) {
        this.free_qty = free_qty;
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