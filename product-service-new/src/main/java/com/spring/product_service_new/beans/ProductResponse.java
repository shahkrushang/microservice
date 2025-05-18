package com.spring.product_service_new.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse<D> {

    private D data;
    private String message;
    private String code;
    private ErrorDetails errorDetails;
}
