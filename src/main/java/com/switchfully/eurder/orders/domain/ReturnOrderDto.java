package com.switchfully.eurder.orders.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;

public class ReturnOrderDto {
    private String OrderId;
    private HashMap<String, LocalDate> shippingDates;
    private BigDecimal totalCost;

    public ReturnOrderDto(String orderId, HashMap<String, LocalDate> shippingDates, BigDecimal totalPrice) {
        OrderId = orderId;
        this.shippingDates = shippingDates;
        this.totalCost = totalPrice;
    }

    public String getOrderId() {
        return OrderId;
    }

    public HashMap<String, LocalDate> getShippingDates() {
        return shippingDates;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

}
