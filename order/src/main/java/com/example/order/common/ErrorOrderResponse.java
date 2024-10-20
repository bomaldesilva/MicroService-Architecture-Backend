package com.example.order.common;

import lombok.Getter;

@Getter
public class ErrorOrderResponse implements OrderResponse{
    private final String order;

    public ErrorOrderResponse(String order) {
        this.order = order;
    }
}
