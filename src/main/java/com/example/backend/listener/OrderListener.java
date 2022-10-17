package com.example.backend.listener;

import com.example.backend.websocket.WebSocketServer;
import com.example.backend.dto.CheckOutData;
import com.example.backend.dto.CheckOutResult;
import com.example.backend.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {
    @Autowired
    OrderService orderService;
    @Autowired
    KafkaTemplate<Object, Object> kSender;
    @Autowired
    WebSocketServer webSocketServer;


    @KafkaListener(topics = "order", groupId = "group_test")
    public void topicOrderListener(ConsumerRecord<String, String> record) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        CheckOutData checkOutData = objectMapper.readValue(record.value(), CheckOutData.class);

        CheckOutResult result = new CheckOutResult();
        result.setResult(orderService.checkOut(checkOutData));
        result.setUsername(checkOutData.getUsername());

        String json = objectMapper.writeValueAsString(result);
        kSender.send("orderr", json);
    }

    @KafkaListener(topics = "orderr", groupId = "group_test")
    public void topicOrderResultListener(ConsumerRecord<String, String> record) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        CheckOutResult result = objectMapper.readValue(record.value(), CheckOutResult.class);
        webSocketServer.sendMessageToUser(result.getUsername(), result.getResult());

    }


}
