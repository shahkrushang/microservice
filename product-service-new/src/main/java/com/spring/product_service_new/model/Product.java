package com.spring.product_service_new.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(value = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Transient
    public static final String SEQUENCE_GENERATOR = "product_sequence";

    @Id
    @Generated
    private Long id;
    private String description;
    private String name;
    private String price;
}
