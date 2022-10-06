package com.example.backend.repository;

import com.example.backend.entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Integer> {
    List<Order> getOrdersByUser_UsernameAndUser_Password(String username, String password);

    List<Order> getOrdersByDateBetween(Date start, Date end);

    Iterable<Order> findOrdersByUserUsernameAndUser_PasswordAndDateBetween(String username, String password, Date start, Date end);
}