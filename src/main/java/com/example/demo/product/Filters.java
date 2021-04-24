package com.example.demo.product;

import java.math.BigDecimal;

public class Filters {
    private String title;
    private String[] types;
    private BigDecimal startPriceRange;
    private BigDecimal endPriceRange;

    public Filters() {
    }

    public Filters(String title, String[] types, BigDecimal startPriceRange, BigDecimal endPriceRange) {
        this.title = title;
        this.types = types;
        this.startPriceRange = startPriceRange;
        this.endPriceRange = endPriceRange;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    public BigDecimal getStartPriceRange() {
        return startPriceRange;
    }

    public void setStartPriceRange(BigDecimal startPriceRange) {
        this.startPriceRange = startPriceRange;
    }

    public BigDecimal getEndPriceRange() {
        return endPriceRange;
    }

    public void setEndPriceRange(BigDecimal endPriceRange) {
        this.endPriceRange = endPriceRange;
    }
}
