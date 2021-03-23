package com.example.demo.product;

import java.math.BigDecimal;

public class LaserWorktable extends Worktable {
    private float electricityConsumes;
    private float cartridgeConsumes;
    private int cartridgeUsageTimes;
    private float timeConsumesForOneUnit;

    public LaserWorktable(Integer id,
                          String title,
                          BigDecimal price,
                          String energyResource,
                          String accuracy,
                          WorktableType worktableType,
                          boolean portable) {
        super(id, title, price, energyResource, accuracy, worktableType, portable);
    }

    public LaserWorktable(Integer id,
                          String title,
                          BigDecimal price,
                          String energyResource,
                          String accuracy,
                          WorktableType worktableType,
                          boolean portable,
                          float electricityConsumes,
                          float cartridgeConsumes,
                          int cartridgeUsageTimes,
                          float timeConsumesForOneUnit) {
        super(id, title, price, energyResource, accuracy, worktableType, portable);
        this.electricityConsumes = electricityConsumes;
        this.cartridgeConsumes = cartridgeConsumes;
        this.cartridgeUsageTimes = cartridgeUsageTimes;
        this.timeConsumesForOneUnit = timeConsumesForOneUnit;
    }

    public float getElectricityConsumes() {
        return electricityConsumes;
    }

    public void setElectricityConsumes(float electricityConsumes) {
        this.electricityConsumes = electricityConsumes;
    }

    public float getCartridgeConsumes() {
        return cartridgeConsumes;
    }

    public void setCartridgeConsumes(float cartridgeConsumes) {
        this.cartridgeConsumes = cartridgeConsumes;
    }

    public int getCartridgeUsageTimes() {
        return cartridgeUsageTimes;
    }

    public void setCartridgeUsageTimes(int cartridgeUsageTimes) {
        this.cartridgeUsageTimes = cartridgeUsageTimes;
    }

    @Override
    public float countEfficiency() {
        return (electricityConsumes + (cartridgeConsumes / cartridgeUsageTimes)) / timeConsumesForOneUnit;
    }
}
