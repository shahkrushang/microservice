package com.spring.product_service_new.model;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Transient
    public static final String USER_SEQUENCE_GENERATOR = "user_sequence";

    @Id
    @Generated
    private Long id;
    private String userName;

}