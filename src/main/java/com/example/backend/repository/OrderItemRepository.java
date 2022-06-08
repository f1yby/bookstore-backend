package com.example.backend.repository;

import com.example.backend.entity.Book;
import com.example.backend.entity.OrderItem;
import com.example.backend.entity.User;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.relational.core.sql.In;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository extends CrudRepository<OrderItem, Integer> {
    Optional<OrderItem> findOrderItemByBook_BidAndUser_UsernameAndUser_Password(Integer bid, String username, String password);

    Optional<OrderItem> findOrderItemByUser_UidAndBook_BidAndIsActivated(Integer uid, Integer bid,boolean isActivate);

    List<OrderItem> findOrderItemsByUser_UsernameAndUser_PasswordAndIsActivated(String username, String password,boolean isActivated);
}