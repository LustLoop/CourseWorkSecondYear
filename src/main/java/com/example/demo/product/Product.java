package com.example.demo.product;

import java.math.BigDecimal;

public abstract class Product {
    private Integer id;
    private String title;
    private BigDecimal price;
    private String energyResource;
    private String accuracy;

    public Product() {
    }

    public Product(Integer id, String title, BigDecimal price, String energyResource, String accuracy) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.energyResource = energyResource;
        this.accuracy = accuracy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
