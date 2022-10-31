package com.switchfully.eurder.domain.orders;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class OrderRepository {
    private HashMap<String, Order> OrdersMap = new HashMap<>();
}
