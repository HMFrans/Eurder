package com.switchfully.eurder.service.orders;


import com.switchfully.eurder.domain.orders.ItemGroup;
import com.switchfully.eurder.domain.orders.Order;
import com.switchfully.eurder.service.orders.dto.ItemGroupReturnDto;
import com.switchfully.eurder.service.orders.dto.OrderOverviewDto;
import com.switchfully.eurder.service.orders.dto.ReturnOrderDto;
import java.util.List;


public class OrderMapper {

    public ReturnOrderDto OrderToReturnOrderDto(Order order) {
        return new ReturnOrderDto(
                order.getId(),
                order.getTotalPrice()
                );
    }

    public List<ReturnOrderDto> orderListToReturnDtoList(List<Order> listOfOrders) {
        return listOfOrders.stream().map(order -> OrderToReturnOrderDto(order)).toList();
    }

    public ItemGroupReturnDto ItemGroupToReturnDto(ItemGroup itemGroup) {
        return new ItemGroupReturnDto(itemGroup.getItemName(), itemGroup.getAmountOrdered(), itemGroup.getShippingDate());
    }

    public OrderOverviewDto createOrderOverviewDto(List<ReturnOrderDto> returnOrderDtoList) {
        return new OrderOverviewDto(returnOrderDtoList);
    }

}
