package com.spring.order.order_service_micro.repository;

import com.spring.order.order_service_micro.model.OrderDetails;
import com.spring.order.order_service_micro.model.OrderLineItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineItemRepository extends JpaRepository<OrderLineItem, Integer> {
}
