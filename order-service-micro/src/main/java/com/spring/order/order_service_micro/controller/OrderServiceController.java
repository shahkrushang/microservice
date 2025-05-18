package com.spring.order.order_service_micro.controller;

import com.spring.order.order_service_micro.dto.OrderDetailRequestDto;
import com.spring.order.order_service_micro.dto.ProductResponseDto;
import com.spring.order.order_service_micro.service.OrderDetailService;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderServiceController {


    @Autowired
    private OrderDetailService detailService;


    @GetMapping("/hello")
    public String hello(){
        return "hello  microservice project";
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<ProductResponseDto> placeOrder(@RequestBody OrderDetailRequestDto itemDetailsRequestDto){
        ResponseEntity response;
        ProductResponseDto  productResponseDto = detailService.SavePlacedOrder(itemDetailsRequestDto);

        return ResponseEntity.ok(productResponseDto);
    }


}
