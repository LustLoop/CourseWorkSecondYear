package com.example.demo.product;

import java.math.BigDecimal;

public class Tool extends Product {
    private ToolType type;
    private boolean consumable;
    private boolean rechargeable;

    public Tool(ToolType type, boolean consumable, boolean rechargeable) {
        this.type = type;
        this.consumable = consumable;
        this.rechargeable = rechargeable;
    }

    public Tool(Integer id,
                String title,
                BigDecimal price,
                String energyResource,
                String accuracy,
                ToolType type,
                boolean consumable,
                boolean rechargeable) {
        super(id, title, price, energyResource, accuracy);
        this.type = type;
        this.consumable = consumable;
        this.rechargeable = rechargeable;
    }

    public ToolType getType() {
        return type;
    }

    public void setType(ToolType type) {
        this.type = type;
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
