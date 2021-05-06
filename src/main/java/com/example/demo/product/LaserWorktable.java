package com.example.demo.product;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LaserWorktable extends Worktable {
    private BigDecimal cartridgeConsumes;
    private BigDecimal cartridgeUsageTimes;

    public LaserWorktable(Integer id,
                          String title,
                          String description,
                          String image,
                          BigDecimal price,
                          String energyResource,
                          String accuracy,
                          TypeOfProduct typeOfProduct,
                          WorktableType worktableType,
                          boolean portable,
                          BigDecimal electricityConsumes,
                          BigDecimal timeConsumesForOneUnit,
                          BigDecimal cartridgeConsumes,
                          BigDecimal cartridgeUsageTimes) {
        super(id, title, description, image, price, energyResource, accuracy, typeOfProduct, worktableType, portable, electricityConsumes, timeConsumesForOneUnit);
        this.cartridgeConsumes = cartridgeConsumes;
        this.cartridgeUsageTimes = cartridgeUsageTimes;
        countEfficiency();
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

    @Override
    public void countEfficiency() {
        this.setEfficiency((this.getElectricityConsumes().add(cartridgeConsumes.divide(cartridgeUsageTimes, 2, RoundingMode.CEILING)))
                .divide(this.getTimeConsumesForOneUnit(), 2, RoundingMode.CEILING));
    }
}
