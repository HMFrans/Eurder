package com.switchfully.eurder.service.items;

import com.switchfully.eurder.domain.items.ItemRepository;
import com.switchfully.eurder.domain.orders.ItemGroup;
import com.switchfully.eurder.domain.orders.ItemGroupRepository;
import com.switchfully.eurder.domain.orders.Order;
import com.switchfully.eurder.service.orders.dto.OrderItemDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ItemGroupService {
    private final int DAYS_TO_SHIP_WHEN_ITEM_NOT_IN_STOCK = 7;
    private final int DAYS_TO_SHIP_WHEN_IN_STOCK = 1;
    private ItemService itemService;
    private ItemGroupRepository itemGroupRepository;

    public ItemGroupService(ItemService itemService) {
        this.itemService = itemService;
    }

    public void createItemGroups(List<OrderItemDto> orderItemDtoList, Order order) {
        orderItemDtoList.forEach(orderItemDto -> addItemGroup(
                new ItemGroup(
                        orderItemDto.getName(),
                        orderItemDto.getAmount(),
                        calculateShippingDate(orderItemDto),
                        calculateItemGroupPrice(orderItemDto),
                        order)));
    }
    private void addItemGroup(ItemGroup itemGroup) {
        itemGroupRepository.save(itemGroup);
    }
    private LocalDate calculateShippingDate(OrderItemDto orderItemDto) {
        int itemAmount = itemService.getItemAmountInStock(orderItemDto.getName());
        if (itemAmount - orderItemDto.getAmount() <= 0) {
            return LocalDate.now().plusDays(DAYS_TO_SHIP_WHEN_ITEM_NOT_IN_STOCK);
        }
        return LocalDate.now().plusDays(DAYS_TO_SHIP_WHEN_IN_STOCK);
    }

    private BigDecimal calculateItemGroupPrice(OrderItemDto orderItemDto) {
        BigDecimal itemPrice = itemService.getItemPrice(orderItemDto.getName());
        return itemPrice.multiply(BigDecimal.valueOf(orderItemDto.getAmount()));
    }
}
