package com.switchfully.eurder.orders.service;

import com.switchfully.eurder.orders.domain.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    private OrderMapper orderMapper = new OrderMapper();
    private ItemGroupService itemGroupService;

    public OrderService(ItemGroupService itemGroupService) {
        this.itemGroupService = itemGroupService;
    }


    public ReturnOrderDto createNewOrder(List<OrderItemDto> orderItemDtoList) {
        Order newOrder = new Order("member");
        populateOrderWithItemGroups(newOrder, orderItemDtoList);
        newOrder.setTotalPrice(calculateTotalPrice(newOrder.getItemGroupList()));
        return orderMapper.OrderToReturnDto(newOrder);
    }

    private void populateOrderWithItemGroups(Order order, List<OrderItemDto> orderItemDtoList) {
        orderItemDtoList.forEach(orderItemDto -> order.addItemgroupToList(
                new ItemGroup(
                        orderItemDto.getName(),
                        orderItemDto.getAmount(),
                        itemGroupService.calculateShippingDate(orderItemDto),
                        itemGroupService.calculateItemGroupPrice(orderItemDto))));
    }

    private BigDecimal calculateTotalPrice(List<ItemGroup> itemGroupList) {
        return itemGroupList.stream()
                .map((itemGroup -> itemGroup.getItemGroupPrice()))
                .reduce(BigDecimal.valueOf(0), (itemPrice1, itemprice2) -> itemPrice1.add(itemprice2));
    }

}