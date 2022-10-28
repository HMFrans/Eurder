package com.switchfully.eurder.items.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class ItemRepository {

    private List<Item> itemList = new ArrayList<>();


    public Item addItem(Item item) {
        itemList.add(item);
        return item;
    }

    public List<Item> getItems() {
        return itemList;
    }
}
