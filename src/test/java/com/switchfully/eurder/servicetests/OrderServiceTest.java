package com.switchfully.eurder.servicetests;

import com.switchfully.eurder.domain.orders.*;
import com.switchfully.eurder.service.orders.OrderMapper;
import com.switchfully.eurder.service.orders.OrderService;
import com.switchfully.eurder.service.orders.dto.OrderItemDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    OrderRepositoryOld orderRepositoryOld;
    private OrderMapper orderMapper = new OrderMapper();

    @Test
    void givenItemGroupList_returnCorrectOrderPrice() {
        List<OrderItemDto> orderItemDtoListTest = new ArrayList<>();
        orderItemDtoListTest.add(new OrderItemDto("flour", 5));
        Order newOrder = new Order("member", orderItemDtoListTest);

        Assertions.assertEquals(BigDecimal.valueOf(10), newOrder.calculateTotalPrice(newOrder.getItemGroupList()) );
    }

    @Test
    void givenEmptyOrderAndListOfItemsToOrder_AddItemsAsItemGroupsToOrder() {
        List<OrderItemDto> orderItemDtoList = new ArrayList<>();
        orderItemDtoList.add(new OrderItemDto("flour", 5));
        Order newOrder = new Order("member", orderItemDtoList);
        List<ItemGroup> expectedList = new ArrayList<>();
        expectedList.add(new ItemGroup("flour", 5, LocalDate.now().plusDays(1), BigDecimal.valueOf(10)));

        Assertions.assertEquals(expectedList.toString(), newOrder.getItemGroupList().toString());
    }

    @Test
    void givenNewOrder_OrderIsAddedToRepository() {
        List<OrderItemDto> orderItemDtoList = new ArrayList<>();
        orderItemDtoList.add(new OrderItemDto("flour", 1));
        orderService.createNewOrder("memberForTest", orderItemDtoList);

        Assertions.assertTrue(orderRepositoryOld.getAllOrders().values().stream().anyMatch(order -> order.getCustomer().equals("memberForTest")));
    }




}