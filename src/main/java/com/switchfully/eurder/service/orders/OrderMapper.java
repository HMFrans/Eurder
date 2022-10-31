package com.switchfully.eurder.service.orders;

import com.switchfully.eurder.domain.orders.Order;
import com.switchfully.eurder.domain.orders.ReturnOrderDto;

import java.time.LocalDate;
import java.util.HashMap;

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
