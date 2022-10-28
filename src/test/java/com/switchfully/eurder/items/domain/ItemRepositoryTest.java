package com.switchfully.eurder.items.domain;

import com.switchfully.eurder.items.service.ItemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ItemRepositoryTest {
    @Autowired
    private ItemService itemService;
    @Autowired
    ItemRepository itemRepository;

    @Test
    void givenOrderedAmount_StockLevelIsReduced() {
        itemRepository.reduceStockLevel("flourTest1", 1);

        assertEquals(9, itemRepository.getItems().get("flourTest1").getAmount());
    }

    @Test
    void givenOrderedAmountMoreThanStockLevel_StockLevelBecomesZero() {
        itemRepository.reduceStockLevel("flourTest2", 15);

        assertEquals(0, itemRepository.getItems().get("flourTest2").getAmount());
    }
}