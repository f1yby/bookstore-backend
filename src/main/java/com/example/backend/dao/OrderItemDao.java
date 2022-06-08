package com.example.backend.dao;

import com.example.backend.entity.OrderItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrderItemDao {
    Optional<OrderItem> findOrderItemByBook_BidAndUser_UsernameAndUser_Password(Integer bid, String username, String password);

    Optional<OrderItem> findOrderItemByUser_UidAndBook_BidAndIsActivated(Integer uid, Integer bid, boolean isActivate);

    List<OrderItem> findOrderItemsByUser_UsernameAndUser_PasswordAndIsActivated(String username, String password, boolean isActivated);

    Iterable<OrderItem> findAllById(List<Integer> oiid);

    void saveAll(Iterable<OrderItem> orderItems);

    void save(OrderItem orderItem);

    void delete(OrderItem orderItem);
}