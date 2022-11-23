package com.switchfully.eurder.service.orders.dto;

import java.math.BigDecimal;
import java.util.List;

public class ReturnOrderDto {
    private Integer orderId;
    private List<ItemGroupReturnDto> itemGroupReturnDtoList;
    private BigDecimal totalCost;

    public ReturnOrderDto(Integer orderId, BigDecimal totalPrice) {
        this.orderId = orderId;
        this.totalCost = totalPrice;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public List<ItemGroupReturnDto> getItemGroupReturnDtoList() {
        return itemGroupReturnDtoList;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }



    public void setItemGroupReturnDtoList(List<ItemGroupReturnDto> itemGroupReturnDtoList) {
        this.itemGroupReturnDtoList = itemGroupReturnDtoList;
    }
}
