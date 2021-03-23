package com.example.demo.product;

import java.math.BigDecimal;

public class Worktable extends Product implements CountableEfficiency {
    private WorktableType worktableType;
    private boolean portable;

    public Worktable(Integer id,
                     String title,
                     BigDecimal price,
                     String energyResource,
                     String accuracy,
                     WorktableType worktableType,
                     boolean portable) {
        super(id, title, price, energyResource, accuracy);
        this.portable = portable;
        this.worktableType = worktableType;
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

    @Override
    public float countEfficiency() {
        return 0;
    }
}
