package com.ecommerce.orderservice.Service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.ecommerce.orderservice.Dto.OrderLineItemDto;
import com.ecommerce.orderservice.Dto.OrderRequest;
import com.ecommerce.orderservice.Event.OrderPlacedEvent;
import com.ecommerce.orderservice.Dto.InventoryResponse;
import com.ecommerce.orderservice.Model.OrderLineItem;
import com.ecommerce.orderservice.Model.Order;
import com.ecommerce.orderservice.Repository.OrderRepository;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
	private ObservationRegistry observationRegistry;
	private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public String placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItem> orderLineItems = orderRequest.getOrderLineItemDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineItem::getSkuCode)
                .toList();

        Observation inventoryServiceObservation = Observation.createNotStarted("inventory-service-lookup",
                this.observationRegistry);
        inventoryServiceObservation.lowCardinalityKeyValue("call", "inventory-service");
        return inventoryServiceObservation.observe(() -> {
	        InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
	                    .uri("http://inventory-service/api/inventory",
	                            uriBuilder -> uriBuilder.queryParam("skuCodes", skuCodes).build())
	                    .retrieve()
	                    .bodyToMono(InventoryResponse[].class)
	                    .block();
	
	        boolean allProductsInStock = Arrays.stream(inventoryResponseArray)
	                    .allMatch(InventoryResponse::isInStock);
	
	        if (allProductsInStock) {
	        	orderRepository.save(order);
				kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(order.getOrderNumber()));
	            return "Order Placed";
	        } else {
	            throw new IllegalArgumentException("Product is not in stock, please try again later!");
	        }
        });
        
    }

    private OrderLineItem mapToDto(OrderLineItemDto orderLineItemDto) {
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setPrice(orderLineItemDto.getPrice());
        orderLineItem.setQuantity(orderLineItemDto.getQuantity());
        orderLineItem.setSkuCode(orderLineItemDto.getSkuCode());
        return orderLineItem;
    }
}