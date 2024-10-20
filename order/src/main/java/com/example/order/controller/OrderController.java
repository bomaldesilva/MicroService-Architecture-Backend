package com.example.order.controller;

import com.example.inventory.dto.OrderEventDTO;
import com.example.order.common.OrderResponse;
import com.example.order.dto.OrderDto;
import com.example.order.kafka.OrderProducer;
import com.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderProducer oderProducer;

    @GetMapping("/getOrders")
    public List<OrderDto> getOrders(){
       return orderService.getAll();
    }
    @PostMapping("addOrder")
    public OrderResponse addOrder(@RequestBody OrderDto order){
        OrderEventDTO orderEventDTO = new OrderEventDTO();
        orderEventDTO.setMessage("Order is committed");
        orderEventDTO.setStatus("pending");
        oderProducer.sendMessage(orderEventDTO);
        return orderService.addOrder(order);
    }
    @PutMapping("updateOrder")
    public OrderResponse updateOrder(@RequestBody OrderDto order){
        return orderService.addOrder(order);
    }
    @DeleteMapping("deleteOrder")
    public String deleteOrder(@RequestBody OrderDto order){
        return orderService.deleteOrder(order);
    }
}
