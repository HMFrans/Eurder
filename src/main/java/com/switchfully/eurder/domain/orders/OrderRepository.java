package com.switchfully.eurder.domain.orders;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class OrderRepository {
    private HashMap<String, Order> ordersMap = new HashMap<>();


    public void addOrder(Order order) {
        ordersMap.put(order.getOrderId(), order);
    }

    public HashMap<String, Order> getAllOrders() {
        return ordersMap;
    }
}
