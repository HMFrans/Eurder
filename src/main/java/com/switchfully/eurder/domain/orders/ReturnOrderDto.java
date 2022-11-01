package com.switchfully.eurder.domain.orders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;

public class ReturnOrderDto {
    private String orderId;
    private String orderedBy;
    private HashMap<String, LocalDate> shippingDates;
    private BigDecimal totalCost;

    public ReturnOrderDto(String orderId, String orderedBy, HashMap<String, LocalDate> shippingDates, BigDecimal totalPrice) {
        this.orderId = orderId;
        this.orderedBy = orderedBy;
        this.shippingDates = shippingDates;
        this.totalCost = totalPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public HashMap<String, LocalDate> getShippingDates() {
        return shippingDates;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public String getOrderedBy() {
        return orderedBy;
    }
}
