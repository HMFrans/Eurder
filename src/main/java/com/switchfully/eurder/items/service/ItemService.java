package com.switchfully.eurder.items.service;


import com.switchfully.eurder.items.domain.Item;
import com.switchfully.eurder.items.domain.addItemDto;
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

    public addItemDto addItem(addItemDto addItemDto) {
        validator.checkRequiredFieldsForNewItem(addItemDto);
        Item newItem = itemMapper.dtoToItem(addItemDto);

        return itemMapper.itemToDto(itemRepository.addItem(newItem));
    }
}
