package com.switchfully.eurder.items.domain;

public class ItemMapper {

    public Item dtoToItem(ItemDto itemDto) {
        return new Item(itemDto.getName(), itemDto.getDescription(), itemDto.getPrice(), itemDto.getAmount());
    }

    public ItemDto itemToDto(Item item) {
        return new ItemDto(item.getName(), item.getDescription(), item.getPrice(), item.getAmount());
    }
}
