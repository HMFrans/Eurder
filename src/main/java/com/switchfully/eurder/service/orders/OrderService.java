package com.switchfully.eurder.service.orders;

import com.switchfully.eurder.domain.items.ItemRepository;
import com.switchfully.eurder.domain.orders.*;
import com.switchfully.eurder.service.Validator;
import com.switchfully.eurder.service.items.ItemGroupService;
import com.switchfully.eurder.service.orders.dto.OrderItemDto;
import com.switchfully.eurder.service.orders.dto.ReturnOrderDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderMapper orderMapper = new OrderMapper();
    private final ItemGroupService itemGroupService;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final Validator validator = new Validator();

    public OrderService(ItemGroupService itemGroupService, ItemRepository itemRepository, OrderRepository orderRepository) {
        this.itemGroupService = itemGroupService;
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
    }


    public ReturnOrderDto createNewOrder(String userName, List<OrderItemDto> orderItemDtoList) {
        validator.checkItemListOnNewOrder(orderItemDtoList);
        Order newOrder = new Order(userName, orderItemDtoList);
        orderRepository.addOrder(newOrder);
        reduceStockLevels(orderItemDtoList);
        return orderMapper.OrderToReturnDto(newOrder);
    }

    private void reduceStockLevels(List<OrderItemDto> orderItemDtoList) {
        orderItemDtoList.forEach(orderItemDto -> itemRepository.reduceStockLevel(orderItemDto.getName(), orderItemDto.getAmount()));
    }

    public List<ReturnOrderDto> getAllOrdersByMember(String userName) {
        List<Order> listOfOrders = orderRepository.getAllOrders().values().stream()
                .filter(order -> order.getMemberId().equals(userName))
                .toList();
        return orderMapper.orderListToReturnDtoList(listOfOrders);
    }
}
