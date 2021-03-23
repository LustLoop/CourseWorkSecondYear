package com.example.demo.product;

import java.math.BigDecimal;

public class HydraulicWorktable extends Worktable {
    private float electricityConsumes;
    private float timeConsumesForOneUnit;

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
                              float electricityConsumes,
                              float timeConsumesForOneUnit) {
        super(id, title, price, energyResource, accuracy, worktableType, portable);
        this.electricityConsumes = electricityConsumes;
        this.timeConsumesForOneUnit = timeConsumesForOneUnit;
    }

    public float getElectricityConsumes() {
        return electricityConsumes;
    }

    public void setElectricityConsumes(float electricityConsumes) {
        this.electricityConsumes = electricityConsumes;
    }

    public float getTimeConsumesForOneUnit() {
        return timeConsumesForOneUnit;
    }

    public void setTimeConsumesForOneUnit(float timeConsumesForOneUnit) {
        this.timeConsumesForOneUnit = timeConsumesForOneUnit;
    }

    @Override
    public float countEfficiency() {
        return electricityConsumes / timeConsumesForOneUnit;
    }
}
