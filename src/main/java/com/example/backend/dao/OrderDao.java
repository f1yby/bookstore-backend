package com.example.backend.dao;

import com.example.backend.entity.Order;

import java.sql.Date;
import java.util.List;

public interface OrderDao {
    List<Order> getOrdersByUser_UsernameAndUser_Password(String username, String password);

    void save(Order order);

    Iterable<Order> findAll();

    Iterable<Order> findOrdersByPeriod(Date start,Date end);

    Iterable<Order> findOrdersByPeriodAndUser_UsernameAndUser_Password(String username, String password, Date start, Date end);
}