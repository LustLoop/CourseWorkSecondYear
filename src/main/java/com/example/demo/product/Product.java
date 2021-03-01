package com.example.demo.product;

import java.math.BigDecimal;

public abstract class Product {
    private String title;
    private BigDecimal price;
    private String energyResource;
    private String accuracy;

    public Product() {
    }

    public Product(String title, BigDecimal price, String energyResource, String accuracy) {
        this.title = title;
        this.price = price;
        this.energyResource = energyResource;
        this.accuracy = accuracy;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEnergyResource() {
        return energyResource;
    }

    public void setEnergyResource(String energyResource) {
        this.energyResource = energyResource;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
