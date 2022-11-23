package com.switchfully.eurder.service.orders.dto;

import java.time.LocalDate;

public class ItemGroupReturnDto {
    private String name;
    private Integer amount_ordered;
    private LocalDate shippingDate;

    public ItemGroupReturnDto(String name, Integer amount_ordered, LocalDate shippingDate) {
        this.name = name;
        this.amount_ordered = amount_ordered;
        this.shippingDate = shippingDate;
    }

    public String getName() {
        return name;
    }

    public Integer getAmount_ordered() {
        return amount_ordered;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }


}
