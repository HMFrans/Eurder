package com.switchfully.eurder.service.orders;

import com.switchfully.eurder.domain.orders.Order;
import com.switchfully.eurder.service.orders.dto.ReturnOrderDto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class OrderMapper {
    public ReturnOrderDto OrderToReturnDto(Order order) {
        return new ReturnOrderDto(order.getOrderId(),
                order.getMemberId(), createOrderOverview(order),
                order.getTotalPrice());
    }

    private HashMap<String, LocalDate> createOrderOverview(Order order) {
        HashMap<String, LocalDate> orderOverview = new HashMap<>();
        order.getItemGroupList().forEach(itemGroup -> orderOverview.put(itemGroup.getItemName(), itemGroup.getShippingDate()));
        return orderOverview;
    }

    public List<ReturnOrderDto> orderListToReturnDtoList(List<Order> listOfOrders) {
        return listOfOrders.stream().map(order -> OrderToReturnDto(order)).toList();
    }
}
