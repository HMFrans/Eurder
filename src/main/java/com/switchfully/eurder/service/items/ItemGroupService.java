package com.switchfully.eurder.service.items;

import com.switchfully.eurder.domain.items.ItemRepository;
import com.switchfully.eurder.service.orders.dto.OrderItemDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class ItemGroupService {
    private final int DAYS_TO_SHIP_WHEN_ITEM_NOT_IN_STOCK = 7;
    private final int DAYS_TO_SHIP_WHEN_IN_STOCK = 1;
    private ItemRepository itemRepository;

    public ItemGroupService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    public LocalDate calculateShippingDate(OrderItemDto orderItemDto) {
        int itemAmount = getItemAmount(orderItemDto);
        if (itemAmount - orderItemDto.getAmount() <= 0) {
            return LocalDate.now().plusDays(DAYS_TO_SHIP_WHEN_ITEM_NOT_IN_STOCK);
        }
        return LocalDate.now().plusDays(DAYS_TO_SHIP_WHEN_IN_STOCK);
    }
    private int getItemAmount(OrderItemDto orderItemDto) {
        return itemRepository.getItems().get(orderItemDto.getName()).getAmount();
    }
    public BigDecimal calculateItemGroupPrice(OrderItemDto orderItemDto) {
        BigDecimal itemPrice = itemRepository.getItems().get(orderItemDto.getName()).getPrice();
        return itemPrice.multiply(BigDecimal.valueOf(orderItemDto.getAmount()));
    }


}
