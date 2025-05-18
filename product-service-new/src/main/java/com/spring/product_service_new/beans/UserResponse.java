package com.spring.product_service_new.beans;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class UserResponse<D> {

    private D data;
    private String message;
    private String code;
    private ErrorDetails errorDetails;
}

