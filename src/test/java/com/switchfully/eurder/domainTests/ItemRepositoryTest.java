package com.switchfully.eurder.domainTests;

import com.switchfully.eurder.domain.items.ItemRepository;
import com.switchfully.eurder.service.items.ItemService;
import com.switchfully.eurder.service.orders.OrderService;
import com.switchfully.eurder.service.orders.dto.OrderItemDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    OrderService orderService;

    @Test
    void givenOrderedAmount_StockLevelIsReduced() {
        List<OrderItemDto> orderItemDtos = new ArrayList<>();
        orderItemDtos.add(new OrderItemDto("flour", 1));

        orderService.reduceStockLevels(orderItemDtos);

        assertEquals(19, itemRepository.findByName("flour").getAmountInStock());
    }

    @Test
    void givenOrderedAmountMoreThanStockLevel_StockLevelBecomesZero() {
        List<OrderItemDto> orderItemDtos = new ArrayList<>();
        orderItemDtos.add(new OrderItemDto("egg", 10));

        orderService.reduceStockLevels(orderItemDtos);

        assertEquals(0, itemRepository.findByName("egg").getAmountInStock());
    }
}