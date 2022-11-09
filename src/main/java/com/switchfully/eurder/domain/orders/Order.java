package com.switchfully.eurder.domain.orders;

import com.switchfully.eurder.domain.items.ItemRepository;
import com.switchfully.eurder.service.items.ItemGroupService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {

    private final ItemGroupService itemGroupService = new ItemGroupService(new ItemRepository());

    private String orderId;
    private List<ItemGroup> itemGroupList;
    private BigDecimal totalPrice = BigDecimal.valueOf(0);
    private String memberId;

    public Order(String memberId, List<OrderItemDto> orderItemDtoList) {
        this.orderId = UUID.randomUUID().toString();
        this.itemGroupList = new ArrayList<>();
        populateOrderWithItemGroups(orderItemDtoList);
        this.memberId = memberId;
        this.totalPrice = calculateTotalPrice(itemGroupList);
    }

    public void populateOrderWithItemGroups(List<OrderItemDto> orderItemDtoList) {
        orderItemDtoList.forEach(orderItemDto -> this.addItemgroupToList(
                new ItemGroup(
                        orderItemDto.getName(),
                        orderItemDto.getAmount(),
                        itemGroupService.calculateShippingDate(orderItemDto),
                        itemGroupService.calculateItemGroupPrice(orderItemDto))));
    }

    public void addItemgroupToList(ItemGroup itemGroup) {
        itemGroupList.add(itemGroup);
    }

    public BigDecimal calculateTotalPrice(List<ItemGroup> itemGroupList) {
        return itemGroupList.stream()
                .map((itemGroup -> itemGroup.getItemGroupPrice()))
                .reduce(BigDecimal.valueOf(0), (itemPrice1, itemprice2) -> itemPrice1.add(itemprice2));
    }


    public String getOrderId() {
        return orderId;
    }

    public List<ItemGroup> getItemGroupList() {
        return itemGroupList;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public String getMemberId() {
        return memberId;
    }
}
