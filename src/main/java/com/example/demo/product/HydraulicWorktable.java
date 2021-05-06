package com.example.demo.product;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class HydraulicWorktable extends Worktable {
    public HydraulicWorktable(Integer id,
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
                              BigDecimal timeConsumesForOneUnit) {
        super(id, title, description, image, price, energyResource, accuracy, typeOfProduct, worktableType, portable, electricityConsumes, timeConsumesForOneUnit);
        countEfficiency();
    }

    @Override
    public void countEfficiency() {
        this.setEfficiency(this.getElectricityConsumes().divide(this.getTimeConsumesForOneUnit(), 2, RoundingMode.CEILING));
    }
}
