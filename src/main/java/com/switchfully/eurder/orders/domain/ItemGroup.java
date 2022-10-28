package com.switchfully.eurder.orders.domain;

import com.switchfully.eurder.items.domain.ItemRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;


public class ItemGroup {
    private String itemName;
    private Integer amountOrdered;
    private LocalDate shippingDate;
    private BigDecimal itemGroupPrice;
    private final int DAYS_TO_SHIP_WHEN_ITEM_NOT_IN_STOCK = 7;
    private final int DAYS_TO_SHIP_WHEN_IN_STOCK = 1;

    private ItemRepository itemRepository;

    public ItemGroup(String itemName, int amountOrdered, LocalDate shippingDate, BigDecimal itemGroupPrice) {
        this.itemName = itemName;
        this.amountOrdered = amountOrdered;
        this.shippingDate = shippingDate;
        this.itemGroupPrice = itemGroupPrice;
    }



    public BigDecimal getItemGroupPrice() {
        return itemGroupPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    //ToDo if item ordered amount should be decreased
}
