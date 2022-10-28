package com.switchfully.eurder.items.domain;

public class ItemMapper {

    public Item dtoToItem(addItemDto addItemDto) {
        return new Item(addItemDto.getName(), addItemDto.getDescription(), addItemDto.getPrice(), addItemDto.getAmount());
    }

    public addItemDto itemToDto(Item item) {
        return new addItemDto(item.getName(), item.getDescription(), item.getPrice(), item.getAmount());
    }
}
