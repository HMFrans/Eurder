package com.switchfully.eurder.orders.domain;

import com.switchfully.eurder.items.domain.ItemRepository;

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

    public ItemGroup(String itemName, int amountOrdered) {
        this.itemName = itemName;
        this.amountOrdered = amountOrdered;
        this.shippingDate = calculateShippingDate(itemName);
        this.itemGroupPrice = calculateItemGroupPrice(itemName);
    }

    private BigDecimal calculateItemGroupPrice(String itemName) {
        BigDecimal itemPrice = itemRepository.getItems().get(itemName).getPrice();
        return itemPrice.multiply(BigDecimal.valueOf(amountOrdered));
    }

    private LocalDate calculateShippingDate(String itemName) {
        int itemAmount = getItemAmount(itemName);
        if (itemAmount == 0) {
            return LocalDate.now().plusDays(DAYS_TO_SHIP_WHEN_ITEM_NOT_IN_STOCK);
        }
        return LocalDate.now().plusDays(DAYS_TO_SHIP_WHEN_IN_STOCK);
    }

    private int getItemAmount(String itemName) {
        return itemRepository.getItems().get(itemName).getAmount();
    }
}
