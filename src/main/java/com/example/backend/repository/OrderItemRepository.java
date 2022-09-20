package com.example.backend.repository;

import com.example.backend.entity.OrderItem;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.Optional;

public interface OrderItemRepository extends CrudRepository<OrderItem, Integer> {

    Optional<OrderItem> findOrderItemByUser_UidAndBook_BidAndActivated(Integer uid, Integer bid, boolean activate);

    Iterable<OrderItem> findAllByUser_UsernameAndUser_PasswordAndActivatedAndOrder_Date(String username, String password, boolean activate, Date date);

    Iterable<OrderItem> findOrderItemsByUser_UsernameAndUser_PasswordAndActivated(String username, String password, boolean isActivated);

    Iterable<OrderItem> findOrderItemsByBook_Name(String bookName);

    Iterable<OrderItem> getOrdersByUser_UsernameAndUser_PasswordAndActivated(String username, String password, boolean activated);
}