package com.switchfully.eurder.orders.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Order {

    private String orderId;
    private List<ItemGroup> itemGroupList;
    private BigDecimal totalPrice = BigDecimal.valueOf(0);
    private String memberId;

    public Order(String memberId) {
        this.orderId = UUID.randomUUID().toString();
        this.itemGroupList = new ArrayList<>();
        this.memberId = memberId;
    }

    public void addItemgroupToList(ItemGroup itemGroup) {
        itemGroupList.add(itemGroup);
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public List<ItemGroup> getItemGroupList() {
        return itemGroupList;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    
}
