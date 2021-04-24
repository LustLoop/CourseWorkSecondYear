package com.example.demo.product;

import java.math.BigDecimal;

public class Worktable extends Product implements CountableEfficiency {
    private WorktableType worktableType;
    private boolean portable;
    private BigDecimal efficiency;
    private BigDecimal electricityConsumes;
    private BigDecimal timeConsumesForOneUnit;

    public Worktable(Integer id,
                     String title,
                     String description,
                     BigDecimal price,
                     String energyResource,
                     String accuracy,
                     TypeOfProduct typeOfProduct) {
        super(id, title, description, price, energyResource, accuracy, typeOfProduct);
    }

    public Worktable(Integer id,
                     String title,
                     String description,
                     BigDecimal price,
                     String energyResource,
                     String accuracy,
                     TypeOfProduct typeOfProduct,
                     WorktableType worktableType,
                     boolean portable,
                     BigDecimal electricityConsumes,
                     BigDecimal timeConsumesForOneUnit) {
        super(id, title, description, price, energyResource, accuracy, typeOfProduct);
        this.worktableType = worktableType;
        this.portable = portable;
        this.electricityConsumes = electricityConsumes;
        this.timeConsumesForOneUnit = timeConsumesForOneUnit;
    }

    public boolean isPortable() {
        return portable;
    }

    public void setPortable(boolean portable) {
        this.portable = portable;
    }

    public WorktableType getWorktableType() {
        return worktableType;
    }

    public void setWorktableType(WorktableType worktableType) {
        this.worktableType = worktableType;
    }

    public BigDecimal getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(BigDecimal efficiency) {
        this.efficiency = efficiency;
    }

    public BigDecimal getElectricityConsumes() {
        return electricityConsumes;
    }

    public void setElectricityConsumes(BigDecimal electricityConsumes) {
        this.electricityConsumes = electricityConsumes;
    }

    public BigDecimal getTimeConsumesForOneUnit() {
        return timeConsumesForOneUnit;
    }

    public void setTimeConsumesForOneUnit(BigDecimal timeConsumesForOneUnit) {
        this.timeConsumesForOneUnit = timeConsumesForOneUnit;
    }

    @Override
    public void countEfficiency() {
    }
}
