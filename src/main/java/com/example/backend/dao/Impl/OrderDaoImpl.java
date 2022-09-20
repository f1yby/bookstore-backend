package com.example.backend.dao.Impl;

import com.example.backend.dao.OrderDao;
import com.example.backend.entity.Order;
import com.example.backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    OrderRepository orderRepository;


    @Override
    public List<Order> getOrdersByUser_UsernameAndUser_Password(String username, String password) {
        return orderRepository.getOrdersByUser_UsernameAndUser_Password(username, password);
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Iterable<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Iterable<Order> findOrdersByPeriod(Date start, Date end) {
        return orderRepository.getOrdersByDateBetween(start, end);
    }

    @Override
    public Iterable<Order> findOrdersByPeriodAndUser_UsernameAndUser_Password(String username, String password, Date start, Date end) {
        return orderRepository.findOrdersByUserUsernameAndUser_PasswordAndDateBetween(username, password, start, end);
    }
}
