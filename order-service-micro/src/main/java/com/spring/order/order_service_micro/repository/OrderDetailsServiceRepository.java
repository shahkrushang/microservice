package com.spring.order.order_service_micro.repository;

import com.spring.order.order_service_micro.model.OrderDetails;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface OrderDetailsServiceRepository extends JpaRepository<OrderDetails, Integer> {
}
