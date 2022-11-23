package com.switchfully.eurder.orderTests;

import com.switchfully.eurder.service.orders.dto.OrderItemDto;
import com.switchfully.eurder.service.orders.ItemGroupService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
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