package com.switchfully.eurder.service.items;


import com.switchfully.eurder.domain.items.Item;
import com.switchfully.eurder.domain.items.ItemRepository;
import com.switchfully.eurder.service.items.dto.AddItemDto;
import com.switchfully.eurder.service.Validator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final Validator validator;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
        this.validator = new Validator();
        this.itemMapper = new ItemMapper();
    }

    public AddItemDto addItem(AddItemDto addItemDto) {
        validator.checkRequiredFieldsForNewItem(addItemDto);
        Item newItem = itemMapper.dtoToItem(addItemDto);
        return itemMapper.itemToDto(itemRepository.save(newItem));
    }

    public int getItemAmountInStock(String name) {
        return itemRepository.findByName(name).getAmountInStock();
    }

    public BigDecimal getItemPrice(String name) {
        return itemRepository.findByName(name).getPrice();
    }
}
