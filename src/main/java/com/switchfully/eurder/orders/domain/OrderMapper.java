package com.switchfully.eurder.orders.domain;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class OrderMapper {
    public ReturnOrderDto OrderToReturnDto(Order order) {
        return new ReturnOrderDto(order.getOrderId(),
                createOrderOverview(order),
                order.getTotalPrice());
    }

    private HashMap<String, LocalDate> createOrderOverview(Order order) {
        HashMap<String, LocalDate> orderOverview = new HashMap<>();
        order.getItemGroupList().forEach(itemGroup -> orderOverview.put(itemGroup.getItemName(), itemGroup.getShippingDate()));
        return orderOverview;
    }
}
