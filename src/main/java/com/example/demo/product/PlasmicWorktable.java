package com.example.demo.product;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PlasmicWorktable extends Worktable {
    private BigDecimal electricityConsumes;
    private BigDecimal gasConsumes;
    private BigDecimal timeConsumesForOneUnit;

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
                            BigDecimal electricityConsumes,
                            BigDecimal gasConsumes,
                            BigDecimal timeConsumesForOneUnit) {
        super(id, title, price, energyResource, accuracy, worktableType, portable);
        this.electricityConsumes = electricityConsumes;
        this.gasConsumes = gasConsumes;
        this.timeConsumesForOneUnit = timeConsumesForOneUnit;
    }

    public BigDecimal getElectricityConsumes() {
        return electricityConsumes;
    }

    public void setElectricityConsumes(BigDecimal electricityConsumes) {
        this.electricityConsumes = electricityConsumes;
    }

    public BigDecimal getGasConsumes() {
        return gasConsumes;
    }

    public void setGasConsumes(BigDecimal gasConsumes) {
        this.gasConsumes = gasConsumes;
    }

    public BigDecimal getTimeConsumesForOneUnit() {
        return timeConsumesForOneUnit;
    }

    public void setTimeConsumesForOneUnit(BigDecimal timeConsumesForOneUnit) {
        this.timeConsumesForOneUnit = timeConsumesForOneUnit;
    }

    @Override
    public BigDecimal countEfficiency() {
        return (electricityConsumes.add(gasConsumes)).divide(timeConsumesForOneUnit, 2, RoundingMode.CEILING);
    }
}
