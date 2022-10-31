package com.switchfully.eurder.servicetests;

import com.switchfully.eurder.domain.items.addItemDto;
import com.switchfully.eurder.domain.items.ItemRepository;
import com.switchfully.eurder.service.items.ItemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class ItemServiceTest {
    @Autowired
    ItemService itemService;
    @Autowired
    ItemRepository itemRepository;

    @Test
    void givenNewItem_ItemIsAddedToRepository() {
        addItemDto addItemDto = new addItemDto("Flower", "crushed grain", new BigDecimal(15.00), 1);
        addItemDto actualAddItemDto = itemService.addItem(addItemDto);

        Assertions.assertTrue(itemRepository.getItems().values().stream().anyMatch(item -> actualAddItemDto.getName().equals(item.getName())));
    }
}