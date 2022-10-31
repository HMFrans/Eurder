package com.switchfully.eurder.domain.items;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;

@Repository
public class ItemRepository {

    private HashMap<String, Item> itemMap;

    public ItemRepository() {
        this.itemMap = new HashMap<>();
        itemMap.put("flour", new Item("flour", "crushed grain", BigDecimal.valueOf(2), 10 ));
        itemMap.put("egg", new Item("egg", "it was here first", BigDecimal.valueOf(1), 10));
        itemMap.put("flourTest1", new Item("egg", "it was here first", BigDecimal.valueOf(1), 10));
        itemMap.put("flourTest2", new Item("egg", "it was here first", BigDecimal.valueOf(1), 10));

    }

    public Item addItem(Item item) {
        itemMap.put(item.getName(), item);
        return item;
    }

    public HashMap<String, Item> getItems() {
        return itemMap;
    }

    public void reduceStockLevel(String itemName, int amountOrdered) {
        int stockAfterOrder = itemMap.get(itemName).getAmount() - amountOrdered;
        if(stockAfterOrder < 0) {
            stockAfterOrder = 0;
        }
        itemMap.get(itemName).setAmount(stockAfterOrder);
    }
}
