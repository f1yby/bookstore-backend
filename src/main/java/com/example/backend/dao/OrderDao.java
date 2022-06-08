package com.example.backend.dao;

import com.example.backend.entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderDao {
    List<Order> getOrdersByUser_UsernameAndUser_Password(String username, String password);

    void save(Order order);
}