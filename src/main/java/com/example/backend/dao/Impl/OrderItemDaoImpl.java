package com.example.backend.dao.Impl;

import com.example.backend.dao.OrderItemDao;
import com.example.backend.entity.OrderItem;
import com.example.backend.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Optional;

@Repository
public class OrderItemDaoImpl implements OrderItemDao {
    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public Optional<OrderItem> findOrderItemByUser_UidAndBook_BidAndActivated(Integer uid, Integer bid, boolean Activate) {
        return orderItemRepository.findOrderItemByUser_UidAndBook_BidAndActivated(uid, bid, Activate);
    }

    @Override
    public Iterable<OrderItem> findOrderItemsByUser_UsernameAndUser_PasswordAndActivated(String username, String password, boolean isActivated) {
        return orderItemRepository.findOrderItemsByUser_UsernameAndUser_PasswordAndActivated(username, password, isActivated);
    }

    @Override
    public Iterable<OrderItem> findOrderItemByUser_UsernameAndUser_PasswordAndActivatedAndOrder_Date(String username, String password, boolean activate, Date date) {
        return orderItemRepository.findAllByUser_UsernameAndUser_PasswordAndActivatedAndOrder_Date(username, password, activate, date);
    }

    @Override
    public Iterable<OrderItem> findAllById(Iterable<Integer> oiid) {
        return orderItemRepository.findAllById(oiid);
    }

    @Override
    public void save(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

    @Override
    public void delete(OrderItem orderItem) {
        orderItemRepository.delete(orderItem);
    }

    @Override
    public Iterable<OrderItem> findAllByBook_Name(String bookName) {
        return orderItemRepository.findOrderItemsByBook_Name(bookName);
    }

    @Override
    public Iterable<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }

    @Override
    public Iterable<OrderItem> getOrdersByUser_UsernameAndUser_PasswordAndActivated(String username, String password, boolean activated) {
        return orderItemRepository.getOrdersByUser_UsernameAndUser_PasswordAndActivated(username, password, activated);
    }
}
