package com.switchfully.eurder.items.domain;

import java.math.BigDecimal;

public class ItemDto {
    private String name;
    private String description;
    private BigDecimal price;
    private int amount;

    public ItemDto(String name, String description, BigDecimal price, int amount) {
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