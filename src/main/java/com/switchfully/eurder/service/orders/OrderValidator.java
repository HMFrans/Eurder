package com.switchfully.eurder.service.orders;

import com.switchfully.eurder.domain.items.ItemRepository;
import com.switchfully.eurder.service.orders.dto.OrderItemDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderValidator {
    ItemRepository itemRepository;

    public OrderValidator(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void validateOrderItemDtoList(List<OrderItemDto> orderItemDtoList) {
        checkItemListOnNewOrder(orderItemDtoList);
        orderItemDtoList.forEach(orderItemDto -> doesItemExist(orderItemDto));
    }

    private void checkItemListOnNewOrder(List<OrderItemDto> orderItemDtoList) {
        if (orderItemDtoList.size() == 0) {
            throw new IllegalArgumentException("No items to add to order");
        }
    }

    private void doesItemExist(OrderItemDto orderItemDto) {
        if (!itemRepository.existsByName(orderItemDto.getName())) {
            throw new IllegalArgumentException("This item does not exist");
        }
    }
}
