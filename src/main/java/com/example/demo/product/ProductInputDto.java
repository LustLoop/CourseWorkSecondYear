package com.example.demo.product;

import java.math.BigDecimal;

import static com.example.demo.product.TypeOfProduct.*;

public class ProductInputDto {
    private Integer id;
    private String title;
    private BigDecimal price;
    private String energyResource;
    private String accuracy;
    private ToolType toolType;
    private boolean consumable;
    private boolean rechargeable;
    private WorktableType worktableType;
    private boolean portable;
    private TypeOfProduct typeOfProduct;

    public ProductInputDto() {
    }

    public ProductInputDto(Integer id,
                           String title,
                           BigDecimal price,
                           String energyResource,
                           String accuracy,
                           ToolType toolType,
                           boolean consumable,
                           boolean rechargeable,
                           TypeOfProduct typeOfProduct) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.energyResource = energyResource;
        this.accuracy = accuracy;
        this.toolType = toolType;
        this.consumable = consumable;
        this.rechargeable = rechargeable;
        this.typeOfProduct = typeOfProduct;
    }

    public ProductInputDto(Integer id,
                           String title,
                           BigDecimal price,
                           String energyResource,
                           String accuracy,
                           WorktableType worktableType,
                           boolean portable,
                           TypeOfProduct typeOfProduct) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.energyResource = energyResource;
        this.accuracy = accuracy;
        this.worktableType = worktableType;
        this.portable = portable;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public ToolType getType() {
        return toolType;
    }

    public void setType(ToolType toolType) {
        this.toolType = toolType;
    }

    public boolean isConsumable() {
        return consumable;
    }

    public void setConsumable(boolean consumable) {
        this.consumable = consumable;
    }

    public boolean isRechargeable() {
        return rechargeable;
    }

    public void setRechargeable(boolean rechargeable) {
        this.rechargeable = rechargeable;
    }

    public WorktableType getWorktableType() {
        return worktableType;
    }

    public void setWorktableType(WorktableType worktableType) {
        this.worktableType = worktableType;
    }

    public boolean isPortable() {
        return portable;
    }

    public void setPortable(boolean portable) {
        this.portable = portable;
    }

    public TypeOfProduct getTypeOfProduct() {
        return typeOfProduct;
    }

    public void setTypeOfProduct(TypeOfProduct typeOfProduct) {
        this.typeOfProduct = typeOfProduct;
    }

    public Product convertToProduct() {
        switch (typeOfProduct) {
            case TOOL:
                return new Tool(id, title, price, energyResource, accuracy, toolType, consumable, rechargeable);
            case WORKTABLE:
                return new Worktable(id, title, price, energyResource, accuracy, worktableType, portable);
            default:
                throw new IncorrectProductTypeException("Incorrect type of product :" + typeOfProduct);
        }
    }
}
