package com.switchfully.eurder.orders.domain;

public class OrderItemDto {
    private String name;
    private Integer amount;

    public OrderItemDto(String name, Integer amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public Integer getAmount() {
        return amount;
    }
}
