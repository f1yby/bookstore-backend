package com.example.backend.service;

import com.example.backend.dto.CheckOutData;
import com.example.backend.entity.Order;
import com.example.backend.entity.OrderItem;

import java.sql.Date;

public interface OrderService {
    String checkOut(CheckOutData checkOutData);

    String addToCart(String username, String password, Integer bid);

    String removeFromCart(String username, String password, Integer bid);


    Iterable<OrderItem> getCart(String username, String password);

    Iterable<Order> getOrdersByUser_UsernameAndUser_Password(String username, String password);

    Iterable<Order> getOrdersByUser_UsernameAndUser_PasswordAndBook_Name(String username, String password, String name);

    Iterable<OrderItem> findAllByUser_UsernameAndUser_PasswordAndActivatedAndOrder_Date(String username, String password, boolean activate, Date date);

    Iterable<Order> getAll(String username, String password);

    Iterable<Order> getAllByBookName(String username, String password, String bookName);

    Iterable<Order> getAllByTimePeriod(String username, String password, Date start, Date end);

    Iterable<Order> getOrdersByUser_UsernameAndUser_PasswordAndBook_NameAndTimePeriod(String username, String password, Date valueOf, Date valueOf1);
}
