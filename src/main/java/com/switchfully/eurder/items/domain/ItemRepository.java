package com.switchfully.eurder.items.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Repository
public class ItemRepository {

    private HashMap<String, Item> itemMap = new HashMap<>();


    public Item addItem(Item item) {
        itemMap.put(item.getName(), item);
        return item;
    }

    public HashMap<String, Item> getItems() {
        return itemMap;
    }
}
