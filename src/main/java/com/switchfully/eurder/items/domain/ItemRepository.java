package com.switchfully.eurder.items.domain;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Repository
public class ItemRepository {

    private HashMap<String, Item> itemMap;

    public ItemRepository() {
        this.itemMap = new HashMap<>();
        itemMap.put("flour", new Item("flour", "crushed grain", BigDecimal.valueOf(2), 10 ));
        itemMap.put("egg", new Item("egg", "it was here first", BigDecimal.valueOf(1), 10));
    }

    public Item addItem(Item item) {
        itemMap.put(item.getName(), item);
        return item;
    }

    public HashMap<String, Item> getItems() {
        return itemMap;
    }
}
