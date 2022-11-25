package com.switchfully.eurder.service.orders.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ReturnOrderDto {
    private final Integer orderId;
    private List<ItemGroupReturnDto> itemGroupReturnDtoList;
    private final BigDecimal totalCost;

    public ReturnOrderDto(Integer orderId, BigDecimal totalPrice) {
        this.orderId = orderId;
        this.totalCost = totalPrice;
        this.itemGroupReturnDtoList = new ArrayList<>();
    }

    public Integer getOrderId() {
        return orderId;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setItemGroupReturnDtoList(List<ItemGroupReturnDto> itemGroupReturnDtoList) {
        this.itemGroupReturnDtoList = itemGroupReturnDtoList;
    }

    public List<ItemGroupReturnDto> getItemGroupReturnDtoList() {
        return itemGroupReturnDtoList;
    }
}
