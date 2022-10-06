package com.example.backend.listener;

import com.example.backend.dto.CheckOutData;
import com.example.backend.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {
    @Autowired
    OrderService orderService;


    @KafkaListener(topics = "order", groupId = "group_test")
    public void topicListener(ConsumerRecord<String, String> record) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        CheckOutData checkOutData = objectMapper.readValue(record.value(), CheckOutData.class);
        System.out.println("listened");
        orderService.checkOut(checkOutData);
    }
}
