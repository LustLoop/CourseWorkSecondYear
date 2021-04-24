package com.example.demo.product;

import java.math.BigDecimal;

public class ProductInputDto {
    private Integer id;
    private String title;
    private String description;
    private BigDecimal price;
    private BigDecimal efficiency;
    private String energyResource;
    private String accuracy;
    private ToolType toolType;
    private boolean consumable;
    private boolean rechargeable;
    private WorktableType worktableType;
    private boolean portable;
    private TypeOfProduct typeOfProduct;
    private BigDecimal timeConsumesForOneUnit;
    private BigDecimal electricityConsumes;
    private BigDecimal cartridgeConsumes;
    private BigDecimal cartridgeUsageTimes;
    private BigDecimal gasConsumes;

    public ProductInputDto() {
    }

    public ProductInputDto(Integer id,
                           String title,
                           String description,
                           BigDecimal price,
                           BigDecimal efficiency,
                           String energyResource,
                           String accuracy,
                           ToolType toolType,
                           boolean consumable,
                           boolean rechargeable,
                           WorktableType worktableType,
                           boolean portable,
                           TypeOfProduct typeOfProduct,
                           BigDecimal timeConsumesForOneUnit,
                           BigDecimal electricityConsumes,
                           BigDecimal cartridgeConsumes,
                           BigDecimal cartridgeUsageTimes,
                           BigDecimal gasConsumes) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.efficiency = efficiency;
        this.energyResource = energyResource;
        this.accuracy = accuracy;
        this.toolType = toolType;
        this.consumable = consumable;
        this.rechargeable = rechargeable;
        this.worktableType = worktableType;
        this.portable = portable;
        this.typeOfProduct = typeOfProduct;
        this.timeConsumesForOneUnit = timeConsumesForOneUnit;
        this.electricityConsumes = electricityConsumes;
        this.cartridgeConsumes = cartridgeConsumes;
        this.cartridgeUsageTimes = cartridgeUsageTimes;
        this.gasConsumes = gasConsumes;
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

    public ToolType getToolType() {
        return toolType;
    }

    public void setToolType(ToolType toolType) {
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

    public BigDecimal getTimeConsumesForOneUnit() {
        return timeConsumesForOneUnit;
    }

    public void setTimeConsumesForOneUnit(BigDecimal timeConsumesForOneUnit) {
        this.timeConsumesForOneUnit = timeConsumesForOneUnit;
    }

    public BigDecimal getElectricityConsumes() {
        return electricityConsumes;
    }

    public void setElectricityConsumes(BigDecimal electricityConsumes) {
        this.electricityConsumes = electricityConsumes;
    }

    public BigDecimal getCartridgeConsumes() {
        return cartridgeConsumes;
    }

    public void setCartridgeConsumes(BigDecimal cartridgeConsumes) {
        this.cartridgeConsumes = cartridgeConsumes;
    }

    public BigDecimal getCartridgeUsageTimes() {
        return cartridgeUsageTimes;
    }

    public void setCartridgeUsageTimes(BigDecimal cartridgeUsageTimes) {
        this.cartridgeUsageTimes = cartridgeUsageTimes;
    }

    public BigDecimal getGasConsumes() {
        return gasConsumes;
    }

    public void setGasConsumes(BigDecimal gasConsumes) {
        this.gasConsumes = gasConsumes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(BigDecimal efficiency) {
        this.efficiency = efficiency;
    }

    public Product convertToTool() {
        return new Tool(id, title, description, price, energyResource, accuracy, typeOfProduct);
    }

    public Product convertToWorktable() {
        return new Tool(id, title, description, price, energyResource, accuracy, typeOfProduct, toolType, consumable, rechargeable);
    }

    public HydraulicWorktable convertToHydraulicWorktable() {
        return new HydraulicWorktable(id, title, description, price, energyResource, accuracy, typeOfProduct, worktableType, portable, electricityConsumes, timeConsumesForOneUnit);
    }

    public LaserWorktable convertToLaserWorktable() {
        return new LaserWorktable(id, title, description, price, energyResource, accuracy, typeOfProduct, worktableType, portable, electricityConsumes, timeConsumesForOneUnit, cartridgeConsumes, cartridgeUsageTimes);
    }

    public PlasmicWorktable convertToPlasmicWorktable() {
        return new PlasmicWorktable(id, title, description, price, energyResource, accuracy, typeOfProduct, worktableType, portable, electricityConsumes, timeConsumesForOneUnit, gasConsumes);
    }
}
