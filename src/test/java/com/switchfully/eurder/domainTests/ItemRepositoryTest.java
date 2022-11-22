package com.switchfully.eurder.domainTests;

import com.switchfully.eurder.service.items.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ItemRepositoryTest {
    @Autowired
    private ItemService itemService;
    @Autowired
    ItemRepository itemRepository;

    @Test
    void givenOrderedAmount_StockLevelIsReduced() {
        itemRepository.reduceStockLevel("flourTest1", 1);

        assertEquals(9, itemRepository.getItems().get("flourTest1").getAmountInStock());
    }

    @Test
    void givenOrderedAmountMoreThanStockLevel_StockLevelBecomesZero() {
        itemRepository.reduceStockLevel("flourTest2", 15);

        assertEquals(0, itemRepository.getItems().get("flourTest2").getAmountInStock());
    }
}