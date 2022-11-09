package com.switchfully.eurder.domain.orders;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class OrderRepository {
    private HashMap<String, Order> ordersMap = new HashMap<>();

    public OrderRepository() {
        List<OrderItemDto> orderItemDtoList = new ArrayList<>();
        orderItemDtoList.add(new OrderItemDto("flour", 5));
        ordersMap.put("1", new Order("member", orderItemDtoList));
    }

    public void addOrder(Order order) {
        ordersMap.put(order.getOrderId(), order);
    }

    public HashMap<String, Order> getAllOrders() {
        return ordersMap;
    }
}
