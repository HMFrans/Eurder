package com.switchfully.eurder.service.orders.dto;

import java.math.BigDecimal;
import java.util.List;

public class OrderOverviewDto {
    private List<ReturnOrderDto> returnOrderDtos;
    private BigDecimal TotalPriceOfAllOrders;

    public OrderOverviewDto(List<ReturnOrderDto> returnOrderDtos) {
        this.returnOrderDtos = returnOrderDtos;
        this.TotalPriceOfAllOrders = calculateTotalPriceOfAllOrders(returnOrderDtos);
    }

    private BigDecimal calculateTotalPriceOfAllOrders(List<ReturnOrderDto> returnOrderDtos) {
        return returnOrderDtos.stream()
                .map(returnOrderDto -> returnOrderDto.getTotalCost())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<ReturnOrderDto> getReturnOrderDtos() {
        return returnOrderDtos;
    }

    public BigDecimal getTotalPriceOfAllOrders() {
        return TotalPriceOfAllOrders;
    }
}
