package com.switchfully.eurder.api;

import com.switchfully.eurder.service.orders.dto.OrderItemDto;
import com.switchfully.eurder.service.orders.dto.ReturnOrderDto;
import com.switchfully.eurder.service.orders.OrderService;
import com.switchfully.eurder.security.Feature;
import com.switchfully.eurder.security.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/order")
public class OrderController {

    private final OrderService orderService;
    private final SecurityService securityService;

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    public OrderController(OrderService orderService, SecurityService securityService) {
        this.orderService = orderService;
        this.securityService = securityService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ReturnOrderDto createOrder(@RequestHeader String authorization, @RequestBody List<OrderItemDto> orderItemDtoList) {
        String userName = securityService.validateAuthorization(authorization, Feature.CREATE_ORDER);
        logger.info("New order is created");
        return orderService.createNewOrder(userName, orderItemDtoList);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReturnOrderDto> getAllOrdersByMember(@RequestHeader String authorization) {
        String userName = securityService.validateAuthorization(authorization, Feature.GET_ALL_ORDERS_BY_MEMBER);
        logger.info("Getting all orders for " + userName);
        return orderService.getAllOrdersByMember(userName);
    }



}
