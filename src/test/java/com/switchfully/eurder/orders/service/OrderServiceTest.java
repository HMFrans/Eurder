package com.switchfully.eurder.orders.service;

import com.switchfully.eurder.orders.domain.ItemGroup;
import com.switchfully.eurder.orders.domain.Order;
import com.switchfully.eurder.orders.domain.OrderItemDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    void givenItemGroupList_returnCorrectOrderPrice() {
        List<ItemGroup> itemGroupList = new ArrayList<>();
        itemGroupList.add(new ItemGroup("flour", 15, LocalDate.now(), BigDecimal.valueOf(10)));

        Assertions.assertEquals(BigDecimal.valueOf(10), orderService.calculateTotalPrice(itemGroupList) );
    }

    @Test
    void givenEmptyOrderAndListOfItemsToOrder_AddItemsAsItemGroupsToOrder() {
        Order newOrder = new Order("member");
        List<OrderItemDto> orderItemDtoList = new ArrayList<>();
        orderItemDtoList.add(new OrderItemDto("flour", 5));
        orderService.populateOrderWithItemGroups(newOrder, orderItemDtoList);
        List<ItemGroup> expectedList = new ArrayList<>();
        expectedList.add(new ItemGroup("flour", 5, LocalDate.now().plusDays(1), BigDecimal.valueOf(10)));

        Assertions.assertEquals(expectedList.toString(), newOrder.getItemGroupList().toString());
    }

    @Test
    void givenOrderList_NewOrderIsCreated() {

    }

}