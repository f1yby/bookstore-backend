package com.example.backend.service;

import com.example.backend.entity.Book;
import com.example.backend.entity.Order;
import com.example.backend.entity.OrderItem;
import com.example.backend.entity.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface OrderService {
    String checkOut(String username, String password, List<Integer> oiid);

    String addToCart(String username, String password, Integer bid);

    String removeFromCart(String username, String password, Integer bid);


    List<OrderItem> getCart(String username, String password);

    Iterable<Order> getOrder(String username, String password);
}
