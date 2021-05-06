package com.example.demo.product;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PlasmicWorktable extends Worktable {
    private BigDecimal gasConsumes;

    public PlasmicWorktable(Integer id,
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
                            BigDecimal gasConsumes) {
        super(id, title, description, image, price, energyResource, accuracy, typeOfProduct, worktableType, portable, electricityConsumes, timeConsumesForOneUnit);
        this.gasConsumes = gasConsumes;
        countEfficiency();
    }

    public BigDecimal getGasConsumes() {
        return gasConsumes;
    }

    public void setGasConsumes(BigDecimal gasConsumes) {
        this.gasConsumes = gasConsumes;
    }

    @Override
    public void countEfficiency() {
        System.out.println("shit");
        this.setEfficiency((this.getElectricityConsumes().add(gasConsumes)).divide(this.getTimeConsumesForOneUnit(), 2, RoundingMode.CEILING));
    }
}
