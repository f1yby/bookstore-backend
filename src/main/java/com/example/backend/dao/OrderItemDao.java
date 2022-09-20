package com.example.backend.dao;

import com.example.backend.entity.OrderItem;

import java.sql.Date;
import java.util.Optional;

public interface OrderItemDao {

    Optional<OrderItem> findOrderItemByUser_UidAndBook_BidAndActivated(Integer uid, Integer bid, boolean Activate);

    Iterable<OrderItem> findOrderItemsByUser_UsernameAndUser_PasswordAndActivated(String username, String password, boolean isActivated);

    Iterable<OrderItem> findOrderItemByUser_UsernameAndUser_PasswordAndActivatedAndOrder_Date(String username, String password, boolean activate, Date date);

    Iterable<OrderItem> findAllById(Iterable<Integer> oiid);

    void save(OrderItem orderItem);

    void delete(OrderItem orderItem);

    Iterable<OrderItem> findAllByBook_Name(String bookName);

    Iterable<OrderItem> findAll();

    Iterable<OrderItem> getOrdersByUser_UsernameAndUser_PasswordAndActivated(String username, String password, boolean activated);
}