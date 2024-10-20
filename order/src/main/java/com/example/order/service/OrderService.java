package com.example.order.service;

import com.example.inventory.dto.InventoryDto;
import com.example.order.common.ErrorOrderResponse;
import com.example.order.common.OrderResponse;
import com.example.order.common.SuccesOrderResponse;
import com.example.order.dto.OrderDto;
import com.example.order.model.Order;
import com.example.order.repository.OrderRepo;
import com.example.product.dto.ProductDto;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Service
@Transactional
public class OrderService {
    private final WebClient inventoryWebClient;
    private final WebClient productWebClient;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private ModelMapper modelMapper;

    public OrderService(WebClient inventoryWebClient,WebClient productWebClient,OrderRepo orderRepo,ModelMapper modelMapper) {
        this.modelMapper =modelMapper;
        this.inventoryWebClient = inventoryWebClient;
        this.productWebClient = productWebClient;
        this.orderRepo = orderRepo;
    }

    public List<OrderDto> getAll() {
        List<Order>ordersList= orderRepo.findAll();
        return modelMapper.map(ordersList,new TypeToken<List<OrderDto>>(){}.getType());
    }

    public OrderResponse addOrder(OrderDto order) {

        Integer itemId = order.getItemId();
        try{
            InventoryDto inventoryResponse = inventoryWebClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/item/{itemId}").build(itemId))
                    .retrieve()
                    .bodyToMono(InventoryDto.class)
                    .block();
            System.out.println(inventoryResponse);
            assert inventoryResponse != null;

            Integer productId = inventoryResponse.getProductId();
            ProductDto productResponse = productWebClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/product/{productId}").build(productId))
                    .retrieve()
                    .bodyToMono(ProductDto.class)
                    .block();
            assert productResponse!= null;
            System.out.println("product"+productResponse);
            System.out.println(inventoryResponse.getQuantity());
            if(inventoryResponse.getQuantity() > 0) {
                if (productResponse.getForSale()==1) {
                    orderRepo.save(modelMapper.map(order, Order.class));
                    return new SuccesOrderResponse(order);
                }
                else {
                    return new ErrorOrderResponse("ItemNotAvailable");
                }
            }else {
                return new ErrorOrderResponse("This Item Is not For Sale");
            }
        }catch (WebClientResponseException e) {
            if (e.getStatusCode().is5xxServerError()) {
                return new ErrorOrderResponse("Item not found");
            }}
        return null;
    }

    public String deleteOrder(OrderDto order) {
        orderRepo.delete(modelMapper.map(order,Order.class));
        return "succes";
    }
}
