package com.spring.order.order_service_micro.event;

import lombok.Data;
import lombok.NoArgsConstructor;

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
