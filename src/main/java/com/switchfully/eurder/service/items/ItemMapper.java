package com.switchfully.eurder.service.items;

import com.switchfully.eurder.domain.items.Item;
import com.switchfully.eurder.domain.items.addItemDto;

public class ItemMapper {

    public Item dtoToItem(addItemDto addItemDto) {
        return new Item(addItemDto.getName(), addItemDto.getDescription(), addItemDto.getPrice(), addItemDto.getAmount());
    }

    public addItemDto itemToDto(Item item) {
        return new addItemDto(item.getName(), item.getDescription(), item.getPrice(), item.getAmount());
    }
}
