package com.example.demo.product;

import java.math.BigDecimal;

public abstract class Product {
    private Integer id;
    private String title;
    private String description;
    private BigDecimal price;
    private String energyResource;
    private String accuracy;
    private TypeOfProduct typeOfProduct;

    public Product() {
    }

    public Product(Integer id,
                   String title,
                   String description,
                   BigDecimal price,
                   String energyResource,
                   String accuracy,
                   TypeOfProduct typeOfProduct) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.energyResource = energyResource;
        this.accuracy = accuracy;
        this.typeOfProduct = typeOfProduct;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public TypeOfProduct getTypeOfProduct() {
        return typeOfProduct;
    }

    public void setTypeOfProduct(TypeOfProduct typeOfProduct) {
        this.typeOfProduct = typeOfProduct;
    }
}
