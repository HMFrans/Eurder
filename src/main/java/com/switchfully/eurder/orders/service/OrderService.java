package com.switchfully.eurder.orders.service;

import com.switchfully.eurder.items.domain.Item;
import com.switchfully.eurder.items.domain.ItemRepository;
import com.switchfully.eurder.orders.domain.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {
    private final int DAYS_TO_SHIP_WHEN_ITEM_NOT_IN_STOCK = 7;
    private final int DAYS_TO_SHIP_WHEN_IN_STOCK = 1;
    private OrderMapper orderMapper = new OrderMapper();
    private ItemRepository itemRepository;

    public OrderService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
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
                        calculateShippingDate(orderItemDto),
                        calculateItemGroupPrice(orderItemDto))));
    }

    private BigDecimal calculateTotalPrice(List<ItemGroup> itemGroupList) {
        return itemGroupList.stream()
                .map((itemGroup -> itemGroup.getItemGroupPrice()))
                .reduce(BigDecimal.valueOf(0), (itemPrice1, itemprice2) -> itemPrice1.add(itemprice2));
    }

    private LocalDate calculateShippingDate(OrderItemDto orderItemDto) {
        int itemAmount = getItemAmount(orderItemDto);
        if (itemAmount - orderItemDto.getAmount() <= 0) {
            return LocalDate.now().plusDays(DAYS_TO_SHIP_WHEN_ITEM_NOT_IN_STOCK);
        }
        return LocalDate.now().plusDays(DAYS_TO_SHIP_WHEN_IN_STOCK);
    }


    private int getItemAmount(OrderItemDto orderItemDto) {
        return itemRepository.getItems().get(orderItemDto.getName()).getAmount();
    }

    private BigDecimal calculateItemGroupPrice(OrderItemDto orderItemDto) {
        BigDecimal itemPrice = itemRepository.getItems().get(orderItemDto.getName()).getPrice();
        return itemPrice.multiply(BigDecimal.valueOf(orderItemDto.getAmount()));
    }

}
