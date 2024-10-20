package com.example.order.common;

import com.example.order.dto.OrderDto;
import lombok.Getter;

@Getter
public class SuccesOrderResponse implements OrderResponse{
    private final OrderDto order;

    public SuccesOrderResponse(OrderDto order) {
        this.order = order;
    }
}
