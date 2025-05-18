package com.spring.inventory_service.repository;

import com.spring.inventory_service.model.InventoryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryServiceRepo extends JpaRepository<InventoryDetails, Integer> {

     InventoryDetails findByProductName(String productName);
}
