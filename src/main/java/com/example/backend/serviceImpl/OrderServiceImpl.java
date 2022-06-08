package com.example.backend.serviceImpl;

import com.example.backend.dao.BookDao;
import com.example.backend.dao.OrderDao;
import com.example.backend.dao.OrderItemDao;
import com.example.backend.dao.UserDao;
import com.example.backend.entity.Book;
import com.example.backend.entity.Order;
import com.example.backend.entity.OrderItem;
import com.example.backend.entity.User;
import com.example.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderItemDao orderItemDao;

    @Autowired
    private BookDao bookDao;


    public String checkOut(String username, String password, List<Integer> oiid) {
        if (oiid.isEmpty()) {
            return null;
        }
        Optional<User> optionalUser = userDao.findUserByUsernameAndPassword(username, password);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (Objects.equals(user.getUserPrivilege().getPrivilege(), "Normal")) {
                Iterable<OrderItem> orderItems = orderItemDao.findAllById(oiid);
                orderItems.forEach(orderItem -> orderItem.setActivated(false));
                Order order = new Order();
                order.setOrderItems((List<OrderItem>) orderItems);
                order.setUser(user);
                order.setOrderItems((List<OrderItem>) orderItems);
                orderDao.save(order);
                orderItemDao.saveAll(orderItems);
                return "OK";
            }
        }
        return "Err";
    }

    public String addToCart(String username, String password, Integer bid) {
        Optional<User> optionalUser = userDao.findUserByUsernameAndPassword(username, password);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (Objects.equals(user.getUserPrivilege().getPrivilege(), "Normal")) {
                Optional<OrderItem> optionalOrderItem = orderItemDao.findOrderItemByUser_UidAndBook_BidAndIsActivated(user.getUid(), bid, true);
                if (optionalOrderItem.isPresent() && optionalOrderItem.get().isActivated()) {
                    OrderItem orderItem = optionalOrderItem.get();
                    orderItem.setCount(optionalOrderItem.get().getCount() + 1);
                    orderItemDao.save(orderItem);
                    return "OK";
                } else {
                    Optional<Book> book = bookDao.findById(bid);
                    if (book.isPresent()) {
                        OrderItem orderItem = new OrderItem();
                        orderItem.setCount(1);
                        orderItem.setUser(user);
                        orderItem.setBook(book.get());
                        orderItem.setActivated(true);
                        orderItemDao.save(orderItem);
                        return "OK";
                    }

                }
            }
        }
        return "Err";
    }


    public String removeFromCart(String username, String password, Integer bid) {
        Optional<User> optionalUser = userDao.findUserByUsernameAndPassword(username, password);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (Objects.equals(user.getUserPrivilege().getPrivilege(), "Normal")) {
                Optional<OrderItem> optionalOrderItem = orderItemDao.findOrderItemByUser_UidAndBook_BidAndIsActivated(user.getUid(), bid, true);
                if (optionalOrderItem.isPresent()) {
                    if (optionalOrderItem.get().getCount() <= 1) {
                        orderItemDao.delete(optionalOrderItem.get());
                    } else {
                        optionalOrderItem.get().setCount(optionalOrderItem.get().getCount() - 1);
                        orderItemDao.save(optionalOrderItem.get());
                    }

                }
                return "OK";
            }
        }
        return "Err";
    }

    public List<OrderItem> getCart(String username, String password) {
        return orderItemDao.findOrderItemsByUser_UsernameAndUser_PasswordAndIsActivated(username, password, true);
    }

    public Iterable<Order> getOrder(String username, String password) {
        return orderDao.getOrdersByUser_UsernameAndUser_Password(username, password);
    }

}
