package com.spring.order.order_service_micro.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailRequestDto {

    @JsonProperty("itemDetailsRequestDto")
    List<ItemDetailsRequestDto> itemDetailsRequestDto;

    public List<ItemDetailsRequestDto> getItemDetailsRequestDto() {
        return itemDetailsRequestDto;
    }

    public void setItemDetailsRequestDto(List<ItemDetailsRequestDto> itemDetailsRequestDto) {
        this.itemDetailsRequestDto = itemDetailsRequestDto;
    }
}
