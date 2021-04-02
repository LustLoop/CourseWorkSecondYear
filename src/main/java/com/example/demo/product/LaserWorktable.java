package com.example.demo.product;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LaserWorktable extends Worktable {
    private BigDecimal electricityConsumes;
    private BigDecimal cartridgeConsumes;
    private BigDecimal cartridgeUsageTimes;
    private BigDecimal timeConsumesForOneUnit;

    public LaserWorktable(Integer id,
                          String title,
                          BigDecimal price,
                          String energyResource,
                          String accuracy,
                          TypeOfProduct typeOfProduct,
                          WorktableType worktableType,
                          boolean portable,
                          BigDecimal electricityConsumes,
                          BigDecimal cartridgeConsumes,
                          BigDecimal cartridgeUsageTimes,
                          BigDecimal timeConsumesForOneUnit) {
        super(id, title, price, energyResource, accuracy, typeOfProduct, worktableType, portable);
        this.electricityConsumes = electricityConsumes;
        this.cartridgeConsumes = cartridgeConsumes;
        this.cartridgeUsageTimes = cartridgeUsageTimes;
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

    @Override
    public BigDecimal countEfficiency() {
        return (electricityConsumes.add(cartridgeConsumes.divide(cartridgeUsageTimes, 2, RoundingMode.CEILING)))
                .divide(timeConsumesForOneUnit, 2, RoundingMode.CEILING);
    }
}
