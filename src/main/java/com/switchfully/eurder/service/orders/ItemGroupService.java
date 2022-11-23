package com.switchfully.eurder.service.orders;

import com.switchfully.eurder.domain.orders.ItemGroup;
import com.switchfully.eurder.domain.orders.ItemGroupRepository;
import com.switchfully.eurder.domain.orders.Order;
import com.switchfully.eurder.service.items.ItemService;
import com.switchfully.eurder.service.orders.dto.ItemGroupReturnDto;
import com.switchfully.eurder.service.orders.dto.OrderItemDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ItemGroupService {
    private final int DAYS_TO_SHIP_WHEN_ITEM_NOT_IN_STOCK = 7;
    private final int DAYS_TO_SHIP_WHEN_IN_STOCK = 1;
    private ItemService itemService;
    private OrderMapper orderMapper = new OrderMapper();
    private ItemGroupRepository itemGroupRepository;

    public ItemGroupService(ItemService itemService, ItemGroupRepository itemGroupRepository) {
        this.itemService = itemService;
        this.itemGroupRepository = itemGroupRepository;
    }

    public void createItemGroupsInDatabase(List<OrderItemDto> orderItemDtoList, Order order) {
        orderItemDtoList.forEach(orderItemDto -> itemGroupRepository.save(
                new ItemGroup(
                        orderItemDto.getName(),
                        orderItemDto.getAmount(),
                        calculateShippingDate(orderItemDto),
                        calculateItemGroupPrice(orderItemDto),
                        order)));
    }

    public LocalDate calculateShippingDate(OrderItemDto orderItemDto) {
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

    public List<ItemGroup> getItemGroupsForOrder(Order order) {
        return itemGroupRepository.findAllByOrder(order);
    }
}
