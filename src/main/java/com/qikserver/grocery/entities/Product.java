package com.qikserver.grocery.entities;

import java.util.List;

public class Product {
    private String id;
    private String name;
    private double price;
    private List<Cupom> promotions;

    public Product(String id, String name, double price, List<Cupom> promotions) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.promotions = promotions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Cupom> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Cupom> promotions) {
        this.promotions = promotions;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", Promotions=" + promotions +
                '}';
    }

}
