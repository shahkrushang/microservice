package com.spring.order.order_service_micro.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.order.order_service_micro.dto.InventoryDetailsResponseDto;
import com.spring.order.order_service_micro.dto.OrderDetailRequestDto;
import com.spring.order.order_service_micro.dto.ProductResponseDto;
import com.spring.order.order_service_micro.event.OrderServiceKafkaEvent;
import com.spring.order.order_service_micro.model.OrderDetails;
import com.spring.order.order_service_micro.model.OrderLineItem;
import com.spring.order.order_service_micro.repository.OrderDetailsServiceRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@Transactional
public class OrderDetailService {

    private final Logger log = LoggerFactory.getLogger(OrderDetailService.class);
    private final KafkaTemplate<String, OrderServiceKafkaEvent> kafkaTemplate;

    public OrderDetailService(KafkaTemplate<String, OrderServiceKafkaEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Autowired
    private OrderDetailsServiceRepository serviceRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;
 
    ObjectMapper objectMapper = new ObjectMapper();

    @CircuitBreaker(name="placeOrderCircuitBreaker", fallbackMethod = "placeOrderCircuitBreaker")
    public ProductResponseDto SavePlacedOrder(OrderDetailRequestDto orderDetailRequestDto){
        OrderDetails detailsPojo = new OrderDetails();
        OrderLineItem orderLineItem = new OrderLineItem();
        detailsPojo.setOrderNumber(String.valueOf(Math.random()));

        orderDetailRequestDto.getItemDetailsRequestDto().forEach(object -> {
            orderLineItem.setItemName(object.getItemName());
            orderLineItem.setItemPrice(object.getItemPrice());
            orderLineItem.setItemCode(object.getItemCode());

        });
        orderLineItem.setOrderDetails(detailsPojo);
        detailsPojo.setOrderLineItemList(List.of(orderLineItem));

        // TODO: 4/7/2025  before saving data check in inventory-service if item is available
        ProductResponseDto productResponseDto = new ProductResponseDto();

        log.info("calling the inventory service.....");
        InventoryDetailsResponseDto responseMono =  webClientBuilder.build().get()//localhost:8085
                .uri("http://inventory-service/inventory/checkProd?productName={productName}", orderDetailRequestDto.getItemDetailsRequestDto().get(0).getItemName())
                        .retrieve()
                .bodyToMono(InventoryDetailsResponseDto.class).block();

        if(ObjectUtils.isEmpty(responseMono) && !ObjectUtils.isEmpty(responseMono.getProductName())) {
            try {
//                InventoryDetailsResponseDto detailsResponseDto =  objectMapper.readValue(responseMono, InventoryDetailsResponseDto.class);
                productResponseDto.setProductCount(responseMono.getProductCount());
                productResponseDto.setProductName(responseMono.getProductName());
                productResponseDto.setMessage("Product Found in DB...");
            } catch (Exception e) {
                e.printStackTrace(); // or handle the error properly
            }
            log.info(responseMono.toString());

        }else {
            //  else throw error
            productResponseDto.setMessage("Product not found hence saving it in DB.. ");
            serviceRepository.save(detailsPojo);
            OrderServiceKafkaEvent orderServiceKafkaEvent = new OrderServiceKafkaEvent();
            orderServiceKafkaEvent.setOrderNumber(detailsPojo.getOrderId());
            kafkaTemplate.send("orderServiceNotification", orderServiceKafkaEvent);
            log.info("order placed sucessfully..!!");
        }
        return productResponseDto;
    }

    public ProductResponseDto placeOrderCircuitBreaker( RuntimeException runtimeException){
        ProductResponseDto dto = new ProductResponseDto();
        log.error("Executing Fallback Method For General exceptions");
        dto.setMessage("this is fallback method, service might be down , please check ..!!");
        return dto;
    }


}
