package com.switchfully.eurder.service.items;


import com.switchfully.eurder.domain.items.Item;
import com.switchfully.eurder.domain.items.addItemDto;
import com.switchfully.eurder.domain.items.ItemRepository;
import com.switchfully.eurder.service.Validator;
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
