package com.switchfully.eurder.service.items;

import com.switchfully.eurder.domain.items.Item;
import com.switchfully.eurder.domain.items.AddItemDto;

public class ItemMapper {

    public Item dtoToItem(AddItemDto addItemDto) {
        return new Item(addItemDto.getName(), addItemDto.getDescription(), addItemDto.getPrice(), addItemDto.getAmount());
    }

    public AddItemDto itemToDto(Item item) {
        return new AddItemDto(item.getName(), item.getDescription(), item.getPrice(), item.getAmount());
    }
}
