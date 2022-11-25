package com.switchfully.eurder.service.items;

import com.switchfully.eurder.domain.items.ItemRepository;
import com.switchfully.eurder.service.items.dto.AddItemDto;
import org.springframework.stereotype.Component;

@Component
public class ItemValidator {

    ItemRepository itemRepository;

    public ItemValidator(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void validateNewItem(AddItemDto addItemDto) {
        checkRequiredFieldsForNewItem(addItemDto);
        checkIfItemAlreadyExists(addItemDto.getName());
    }

    public void checkRequiredFieldsForNewItem(AddItemDto addItemDto) {
        if (addItemDto.getName() == null || addItemDto.getName().equals("")) {
            throw new IllegalArgumentException("Please enter a name.");
        }
        if (addItemDto.getDescription() == null || addItemDto.getDescription().equals("")) {
            throw new IllegalArgumentException("Please enter a description.");
        }
        if (addItemDto.getPrice() == null) {
            throw new IllegalArgumentException("Please enter a price.");
        }
    }

    private void checkIfItemAlreadyExists(String name) {
        if (itemRepository.existsByName(name)) {
            throw new IllegalArgumentException("This item already exists");
        }
    }
}
