package com.example.demo.product;

import java.math.BigDecimal;

public class Tool extends Product {
    private ToolType toolType;
    private boolean consumable;
    private boolean rechargeable;

    public Tool(Integer id,
                String title,
                BigDecimal price,
                String energyResource,
                String accuracy,
                ToolType toolType,
                boolean consumable,
                boolean rechargeable) {
        super(id, title, price, energyResource, accuracy);
        this.toolType = toolType;
        this.consumable = consumable;
        this.rechargeable = rechargeable;
    }

    public ToolType getToolType() {
        return toolType;
    }

    public void setToolType(ToolType toolType) {
        this.toolType = toolType;
    }

    public boolean isConsumable() {
        return consumable;
    }

    public void setConsumable(boolean consumable) {
        this.consumable = consumable;
    }

    public boolean isRechargeable() {
        return rechargeable;
    }

    public void setRechargeable(boolean rechargeable) {
        this.rechargeable = rechargeable;
    }
}
