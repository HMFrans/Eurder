package com.switchfully.eurder.orders.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<ItemGroup> itemGroupList;
    private BigDecimal totalPrice;
    private String memberId;

}
