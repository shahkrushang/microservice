package com.spring.inventory_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InventoryDetailDto {

    @JsonProperty("productName")
    private String productName;

    @JsonProperty("productCount")
    private Integer productCount;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }


    public InventoryDetailDto(String productName, Integer productCount) {
        this.productName = productName;
        this.productCount = productCount;
    }
}
