package com.example.demo.product;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class HydraulicWorktable extends Worktable {
    private BigDecimal electricityConsumes;
    private BigDecimal timeConsumesForOneUnit;

    public HydraulicWorktable(Integer id,
                              String title,
                              BigDecimal price,
                              String energyResource,
                              String accuracy,
                              WorktableType worktableType,
                              boolean portable) {
        super(id, title, price, energyResource, accuracy, worktableType, portable);
    }

    public HydraulicWorktable(Integer id,
                              String title,
                              BigDecimal price,
                              String energyResource,
                              String accuracy,
                              WorktableType worktableType,
                              boolean portable,
                              BigDecimal electricityConsumes,
                              BigDecimal timeConsumesForOneUnit) {
        super(id, title, price, energyResource, accuracy, worktableType, portable);
        this.electricityConsumes = electricityConsumes;
        this.timeConsumesForOneUnit = timeConsumesForOneUnit;
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
    public BigDecimal countEfficiency() {
        return electricityConsumes.divide(timeConsumesForOneUnit, 2, RoundingMode.CEILING);
    }
}
