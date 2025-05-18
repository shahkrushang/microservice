package com.spring.inventory_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.inventory_service.controller.InventoryController;
import com.spring.inventory_service.dto.InventoryDetailDto;
import com.spring.inventory_service.model.InventoryDetails;
import com.spring.inventory_service.repository.InventoryServiceRepo;
import jakarta.transaction.Transactional;
import org.apache.catalina.mapper.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class InventoryDetailsService {

    private final Logger log = LoggerFactory.getLogger(InventoryDetailsService.class);

    @Autowired
    private InventoryServiceRepo inventoryServiceRepo;


    public InventoryDetails checkProduct(String productName){
        log.info("in service call...");
        InventoryDetails inventoryDetails = inventoryServiceRepo.findByProductName(productName);
        log.info("completed processing of the finding data {}, ", productName);
        if(inventoryDetails == null){
            log.warn("ATLEAST HAVE SOME DATA IN DB...!");
            return new InventoryDetails();
        }
        return inventoryDetails;
    }


    public ResponseEntity saveInventoryData(InventoryDetailDto inventoryDetailDto){
        InventoryDetails details = new InventoryDetails();
        details.setProductCount(inventoryDetailDto.getProductCount());
        details.setProductName(inventoryDetailDto.getProductName());
        inventoryServiceRepo.save(details);
        log.info("Data saved in DB.....");
        return ResponseEntity.ok("Data saved");
    }


}
