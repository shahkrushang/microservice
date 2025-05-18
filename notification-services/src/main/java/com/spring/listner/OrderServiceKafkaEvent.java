package com.spring.listner;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderServiceKafkaEvent {

    private int orderNumber;

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public OrderServiceKafkaEvent(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public OrderServiceKafkaEvent() {
    }
}

