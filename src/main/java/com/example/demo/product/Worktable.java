package com.example.demo.product;

import java.math.BigDecimal;

public class Worktable extends Product {
    private boolean portable;
    private WorktableType worktableType;
    private TypeOfWork typeOfWork;

    public Worktable(boolean portable, WorktableType worktableType, TypeOfWork typeOfWork) {
        this.portable = portable;
        this.worktableType = worktableType;
        this.typeOfWork = typeOfWork;
    }

    public Worktable(Integer id, String title, BigDecimal price, String energyResource, String accuracy, boolean portable, WorktableType worktableType, TypeOfWork typeOfWork) {
        super(id, title, price, energyResource, accuracy);
        this.portable = portable;
        this.worktableType = worktableType;
        this.typeOfWork = typeOfWork;
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

    public TypeOfWork getTypeOfWork() {
        return typeOfWork;
    }

    public void setTypeOfWork(TypeOfWork typeOfWork) {
        this.typeOfWork = typeOfWork;
    }
}
