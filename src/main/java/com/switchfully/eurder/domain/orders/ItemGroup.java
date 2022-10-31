package com.switchfully.eurder.domain.orders;

import com.switchfully.eurder.domain.items.ItemRepository;

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

    @Override
    public String toString() {
        return "ItemGroup{" +
                "itemName='" + itemName + '\'' +
                ", amountOrdered=" + amountOrdered +
                ", shippingDate=" + shippingDate +
                ", itemGroupPrice=" + itemGroupPrice +
                ", DAYS_TO_SHIP_WHEN_ITEM_NOT_IN_STOCK=" + DAYS_TO_SHIP_WHEN_ITEM_NOT_IN_STOCK +
                ", DAYS_TO_SHIP_WHEN_IN_STOCK=" + DAYS_TO_SHIP_WHEN_IN_STOCK +
                ", itemRepository=" + itemRepository +
                '}';
    }
    //ToDo item amount needs to be decreased in stock
    //ToDo What if an item is not found?
    //
}