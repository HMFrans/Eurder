package com.switchfully.eurder.service.orders;

import com.switchfully.eurder.domain.customers.Customer;
import com.switchfully.eurder.domain.orders.*;
import com.switchfully.eurder.service.customers.CustomerService;
import com.switchfully.eurder.service.items.ItemService;
import com.switchfully.eurder.service.orders.dto.OrderItemDto;
import com.switchfully.eurder.service.orders.dto.OrderOverviewDto;
import com.switchfully.eurder.service.orders.dto.ReturnOrderDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {

    private final OrderMapper orderMapper;
    private final ItemGroupService itemGroupService;
    private final ItemService itemService;
    private final OrderRepository orderRepository;
    private final OrderValidator orderValidator;
    private final CustomerService customerService;

    public OrderService(OrderMapper orderMapper, ItemGroupService itemGroupService, ItemService itemService, OrderRepository orderRepository, OrderValidator orderValidator, CustomerService customerService) {
        this.orderMapper = orderMapper;
        this.itemGroupService = itemGroupService;
        this.itemService = itemService;
        this.orderRepository = orderRepository;
        this.orderValidator = orderValidator;
        this.customerService = customerService;
    }


    public ReturnOrderDto createNewOrder(String emailAddress, List<OrderItemDto> orderItemDtoList) {
        orderValidator.validateOrderItemDtoList(orderItemDtoList);

        Customer customer = customerService.getCustomerByEmail(emailAddress);
        Order newOrder = orderRepository.save(new Order(customer));

        itemGroupService.createItemGroupsInDatabase(orderItemDtoList, newOrder);
        reduceStockLevels(orderItemDtoList);
        newOrder.setTotalPrice(calculateTotalPrice(itemGroupService.getItemGroupsForOrder(newOrder)));

        return createReturnOrderDto(newOrder);
    }

    private ReturnOrderDto createReturnOrderDto(Order order) {
        ReturnOrderDto returnOrderDto = orderMapper.OrderToReturnOrderDto(order);
        setGroupItemReturnListForReturnOrderDto(order, returnOrderDto);
        return returnOrderDto;
    }

    private void setGroupItemReturnListForReturnOrderDto(Order newOrder, ReturnOrderDto returnOrderDto) {
        returnOrderDto.setItemGroupReturnDtoList(
                itemGroupService.getItemGroupsForOrder(newOrder).stream()
                .map(itemGroup -> orderMapper.ItemGroupToReturnDto(itemGroup))
                .collect(Collectors.toList()));
    }

    public BigDecimal calculateTotalPrice(List<ItemGroup> itemGroupList) {
        return itemGroupList.stream()
                .map(itemGroup -> itemGroup.getItemGroupPrice())
                .reduce(BigDecimal.valueOf(0), (itemPrice1, itemPrice2) -> itemPrice1.add(itemPrice2));
    }

    public void reduceStockLevels(List<OrderItemDto> orderItemDtoList) {
        orderItemDtoList.forEach(orderItemDto -> itemService.reduceStockLevel(orderItemDto));
    }

    public OrderOverviewDto getAllOrdersByMember(String emailAddress) {
        Integer customerId = customerService.getCustomerByEmail(emailAddress).getId();
        List<Order> orderList = orderRepository.findAllByCustomer_Id(customerId).stream().toList();
        List<ReturnOrderDto> returnOrderDtoList = orderList.stream()
                .map(returnOrderDto -> createReturnOrderDto(returnOrderDto))
                .collect(Collectors.toList());
        return orderMapper.createOrderOverviewDto(returnOrderDtoList);
    }


}
