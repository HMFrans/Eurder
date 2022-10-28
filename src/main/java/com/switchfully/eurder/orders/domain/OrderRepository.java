package com.switchfully.eurder.orders.domain;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class OrderRepository {
    private HashMap<String, Order> OrdersMap = new HashMap<>();
}
