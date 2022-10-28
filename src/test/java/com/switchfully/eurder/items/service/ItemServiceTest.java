package com.switchfully.eurder.items.service;

import com.switchfully.eurder.items.domain.Item;
import com.switchfully.eurder.items.domain.ItemDto;
import com.switchfully.eurder.items.domain.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemServiceTest {
    @Autowired
    ItemService itemService;
    @Autowired
    ItemRepository itemRepository;

    @Test
    void givenNewItem_ItemIsAddedToRepository() {
        ItemDto itemDto = new ItemDto("Flower", "crushed grain", new BigDecimal(15.00), 1);
        ItemDto actualItemDto = itemService.addItem(itemDto);

        Assertions.assertTrue(itemRepository.getItems().stream().anyMatch(item -> actualItemDto.getName().equals(item.getName())));
    }
}