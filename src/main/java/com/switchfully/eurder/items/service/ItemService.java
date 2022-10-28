package com.switchfully.eurder.items.service;


import com.switchfully.eurder.items.domain.Item;
import com.switchfully.eurder.items.domain.ItemDto;
import com.switchfully.eurder.items.domain.ItemMapper;
import com.switchfully.eurder.items.domain.ItemRepository;
import com.switchfully.eurder.users.service.Validator;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    private ItemRepository itemRepository;
    private ItemMapper itemMapper;
    private Validator validator;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
        this.validator = new Validator();
        this.itemMapper = new ItemMapper();
    }

    public ItemDto addItem(ItemDto itemDto) {
        validator.checkRequiredFieldsForNewItem(itemDto);
        Item newItem = itemMapper.dtoToItem(itemDto);

        return itemMapper.itemToDto(itemRepository.addItem(newItem));
    }
}
