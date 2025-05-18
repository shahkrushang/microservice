package com.spring;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.listner.OrderServiceKafkaEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class NotificationServiceApplication {

    private final Logger log = LoggerFactory.getLogger(NotificationServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }


    @KafkaListener(topics = "orderServiceNotification")
    public void handleNotification( String value){
//        send out email notification

        log.info("received the notification for the order number: {} ", value);
    }
}