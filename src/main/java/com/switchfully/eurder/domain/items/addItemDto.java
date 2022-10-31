package com.switchfully.eurder.domain.items;

import java.math.BigDecimal;

public class addItemDto {
    private String name;
    private String description;
    private BigDecimal price;
    private int amount;

    public addItemDto(String name, String description, BigDecimal price, int amount) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }
}
