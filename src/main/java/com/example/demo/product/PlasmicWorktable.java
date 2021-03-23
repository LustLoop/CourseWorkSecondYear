package com.example.demo.product;

import java.math.BigDecimal;

public class PlasmicWorktable extends Worktable {
    private float electricityConsumes;
    private float gasConsumes;
    private float timeConsumesForOneUnit;

    public PlasmicWorktable(Integer id,
                            String title,
                            BigDecimal price,
                            String energyResource,
                            String accuracy,
                            WorktableType worktableType,
                            boolean portable) {
        super(id, title, price, energyResource, accuracy, worktableType, portable);
    }

    public PlasmicWorktable(Integer id,
                            String title,
                            BigDecimal price,
                            String energyResource,
                            String accuracy,
                            WorktableType worktableType,
                            boolean portable,
                            float electricityConsumes,
                            float gasConsumes,
                            float timeConsumesForOneUnit) {
        super(id, title, price, energyResource, accuracy, worktableType, portable);
        this.electricityConsumes = electricityConsumes;
        this.gasConsumes = gasConsumes;
        this.timeConsumesForOneUnit = timeConsumesForOneUnit;
    }

    public float getElectricityConsumes() {
        return electricityConsumes;
    }

    public void setElectricityConsumes(float electricityConsumes) {
        this.electricityConsumes = electricityConsumes;
    }

    public float getGasConsumes() {
        return gasConsumes;
    }

    public void setGasConsumes(float gasConsumes) {
        this.gasConsumes = gasConsumes;
    }

    public float getTimeConsumesForOneUnit() {
        return timeConsumesForOneUnit;
    }

    public void setTimeConsumesForOneUnit(float timeConsumesForOneUnit) {
        this.timeConsumesForOneUnit = timeConsumesForOneUnit;
    }

    @Override
    public float countEfficiency() {
        return (electricityConsumes + gasConsumes) / timeConsumesForOneUnit;
    }
}
