package com.spring.inventory_service.controller;

import com.spring.inventory_service.dto.InventoryDetailDto;
import com.spring.inventory_service.model.InventoryDetails;
import com.spring.inventory_service.service.InventoryDetailsService;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final Logger log = LoggerFactory.getLogger(InventoryController.class);
    @Autowired
    private InventoryDetailsService inventoryDetailsService;

    @GetMapping("/helloInv")
    public String startUpInventory(){
        return "Hello Inventory!!";
    }


    @GetMapping("/checkProd")
    public ResponseEntity<InventoryDetails> checkProduct(@PathParam("productName") String productName){
        log.info("process start to find product in inventory....!!");
        InventoryDetails response = inventoryDetailsService.checkProduct(productName);
        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PostMapping("/saveInventory")
    public  ResponseEntity createInventory(@RequestBody InventoryDetailDto inventoryDetailDto){
        if(inventoryDetailDto != null & inventoryDetailDto.getProductCount() != null ){
         return  inventoryDetailsService.saveInventoryData(inventoryDetailDto);
        }
        return ResponseEntity.ok("after if statement , data not saved!..");
    }

}
