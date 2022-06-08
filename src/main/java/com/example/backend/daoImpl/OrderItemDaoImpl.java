package com.example.backend.daoImpl;

import com.example.backend.dao.OrderItemDao;
import com.example.backend.entity.OrderItem;
import com.example.backend.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderItemDaoImpl implements OrderItemDao {
    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public Optional<OrderItem> findOrderItemByBook_BidAndUser_UsernameAndUser_Password(Integer bid, String username, String password) {
        return orderItemRepository.findOrderItemByBook_BidAndUser_UsernameAndUser_Password(bid, username, password);
    }

    @Override
    public Optional<OrderItem> findOrderItemByUser_UidAndBook_BidAndIsActivated(Integer uid, Integer bid, boolean isActivate) {
        return orderItemRepository.findOrderItemByUser_UidAndBook_BidAndIsActivated(uid, bid, isActivate);
    }

    @Override
    public List<OrderItem> findOrderItemsByUser_UsernameAndUser_PasswordAndIsActivated(String username, String password, boolean isActivated) {
        return orderItemRepository.findOrderItemsByUser_UsernameAndUser_PasswordAndIsActivated(username, password, isActivated);
    }

    @Override
    public Iterable<OrderItem> findAllById(List<Integer> oiid) {
        return orderItemRepository.findAllById(oiid);
    }

    @Override
    public void saveAll(Iterable<OrderItem> orderItems) {
        orderItemRepository.saveAll(orderItems);
    }

    @Override
    public void save(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

    @Override
    public void delete(OrderItem orderItem) {
        orderItemRepository.delete(orderItem);
    }
}
