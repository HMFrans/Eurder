package com.switchfully.eurder.orders.service;

import com.switchfully.eurder.orders.domain.ItemGroup;
import com.switchfully.eurder.orders.domain.OrderItemDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemGroupServiceTest {
    @Autowired
    private ItemGroupService itemGroupService;
    @Test
    void givenAmountSmallerThanStockLevel_ShippingDateIsOneDayLater() {
        OrderItemDto orderItemDto = new OrderItemDto("flour", 1);
        LocalDate expected = LocalDate.now().plusDays(1);

        assertEquals(expected, itemGroupService.calculateShippingDate(orderItemDto));
    }
    @Test
    void givenAmountLargerThanStockLevel_ShippingDateIsOneDayLater() {
        OrderItemDto orderItemDto = new OrderItemDto("flour", 500);
        LocalDate expected = LocalDate.now().plusDays(7);

        assertEquals(expected, itemGroupService.calculateShippingDate(orderItemDto));
    }

}