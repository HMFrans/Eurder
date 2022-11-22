package com.switchfully.eurder.service.orders;

import com.switchfully.eurder.domain.customers.Customer;
import com.switchfully.eurder.domain.items.ItemRepository;
import com.switchfully.eurder.domain.orders.*;
import com.switchfully.eurder.service.Validator;
import com.switchfully.eurder.service.customers.CustomerService;
import com.switchfully.eurder.service.items.ItemGroupService;
import com.switchfully.eurder.service.items.ItemService;
import com.switchfully.eurder.service.orders.dto.OrderItemDto;
import com.switchfully.eurder.service.orders.dto.ReturnOrderDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderService {

    private final OrderMapper orderMapper = new OrderMapper();
    private final ItemGroupService itemGroupService;
    private final ItemService itemService;
    private final OrderRepository orderRepository;
    private final Validator validator = new Validator();
    private final CustomerService customerService;

    public OrderService(ItemGroupService itemGroupService, ItemService itemService, OrderRepository orderRepository, CustomerService customerService) {
        this.itemGroupService = itemGroupService;
        this.itemService = itemService;
        this.orderRepository = orderRepository;
        this.customerService = customerService;
    }


    public ReturnOrderDto createNewOrder(String emailAddress, List<OrderItemDto> orderItemDtoList) {
        //check if the itemlist has items and if those items are in the DB
        validator.checkItemListOnNewOrder(orderItemDtoList);
        // create new empty order in the db
        Customer customer = customerService.getCustomerByEmail(emailAddress);
        Order newOrder = orderRepository.save(new Order(customer));
        //create item groups for the items in the list and reference them to the order
        itemGroupService.createItemGroups(orderItemDtoList, newOrder);

        // reduce stock levels of ordered items (can happen in itemgroupservice?)

        // calculate the total price
        reduceStockLevels(orderItemDtoList);
        return orderMapper.OrderToReturnDto(newOrder);
    }

    private void reduceStockLevels(List<OrderItemDto> orderItemDtoList) {
        //TODO fix this so the reduction of stock takes place
        //orderItemDtoList.forEach(orderItemDto -> itemService.reduceStockLevel(orderItemDto.getName(), orderItemDto.getAmount()));
    }

    public List<ReturnOrderDto> getAllOrdersByMember(String emailAddress) {
        return null;
    }


}
