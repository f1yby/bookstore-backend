package com.example.backend.service.Impl;

import com.example.backend.dao.BookDao;
import com.example.backend.dao.OrderDao;
import com.example.backend.dao.OrderItemDao;
import com.example.backend.dao.UserDao;
import com.example.backend.dto.CheckOutData;
import com.example.backend.dto.function.SumBook;
import com.example.backend.entity.Book;
import com.example.backend.entity.Order;
import com.example.backend.entity.OrderItem;
import com.example.backend.entity.User;
import com.example.backend.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
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

    @Transactional(propagation = Propagation.REQUIRED)
    public String checkOut(CheckOutData checkOutData) {
        List<Integer> oiid = checkOutData.getOiid();
        String username = checkOutData.getUsername();
        String password = checkOutData.getPassword();
        if (oiid.isEmpty()) {
            return null;
        }
        Optional<User> optionalUser = userDao.findUserByUsernameAndPassword(username, password);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Order order = new Order();
            if (Objects.equals(user.getPermission(), "Normal")) {
                order.setUser(user);
                order.setDate(Date.valueOf(LocalDate.now()));
                orderDao.save(order);
                Iterable<OrderItem> orderItems = orderItemDao.findAllById(oiid);
                orderItems.forEach(orderItem -> {
                    orderItem.setActivated(false);
                    orderItem.getBook().setInventory(orderItem.getBook().getInventory() - orderItem.getCount());
                    orderItem.setOrder(order);
                    orderItemDao.save(orderItem);
                });

                order.setOrderItems((List<OrderItem>) orderItems);
                orderDao.save(order);
                return "OK";
            }
        }
        return "Invalid User";
    }

    public String addToCart(String username, String password, Integer bid) {
        Optional<User> optionalUser = userDao.findUserByUsernameAndPassword(username, password);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (Objects.equals(user.getPermission(), "Normal")) {
                Optional<OrderItem> optionalOrderItem = orderItemDao.findOrderItemByUser_UidAndBook_BidAndActivated(user.getUid(), bid, true);
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
            if (Objects.equals(user.getPermission(), "Normal")) {
                Optional<OrderItem> optionalOrderItem = orderItemDao.findOrderItemByUser_UidAndBook_BidAndActivated(user.getUid(), bid, true);
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

    public Iterable<OrderItem> getCart(String username, String password) {
        return orderItemDao.findOrderItemsByUser_UsernameAndUser_PasswordAndActivated(username, password, true);
    }

    public Iterable<Order> getOrdersByUser_UsernameAndUser_Password(String username, String password) {
        return orderDao.getOrdersByUser_UsernameAndUser_Password(username, password);
    }

    @Override
    public Iterable<Order> getOrdersByUser_UsernameAndUser_PasswordAndBook_Name(String username, String password, String name) {
        Iterable<OrderItem> orderItems = orderItemDao.getOrdersByUser_UsernameAndUser_PasswordAndActivated(username, password, false);
        HashSet<Order> orderHashMap = new HashSet<>();

        orderItems.forEach(orderItem -> {
            if (!orderItem.isActivated() && orderItem.getBook().getName().equals(name)) {
                orderHashMap.add(orderItem.getOrder());
            }
        });
        return orderHashMap;
    }

    @Override
    public Iterable<OrderItem> findAllByUser_UsernameAndUser_PasswordAndActivatedAndOrder_Date(String username, String password, boolean activate, Date date) {
        return orderItemDao.findOrderItemByUser_UsernameAndUser_PasswordAndActivatedAndOrder_Date(username, password, activate, date);
    }

    @Override
    public BigDecimal sumPrice(Integer oid) {
        Optional<Order> order = orderDao.findById(oid);
        if (order.isPresent()) {
            RestTemplate rest = new RestTemplate();
            ObjectMapper objectMapper = new ObjectMapper();
            final BigDecimal[] sum = {BigDecimal.ZERO};
            order.get().getOrderItems().forEach(item -> {
                try {
                    ResponseEntity<String> result = rest.exchange(RequestEntity.post(new URI("http://localhost:8081/sum")).body(objectMapper.writeValueAsString(new SumBook(item.getBook().getPrice(), item.getCount()))), String.class);
                    sum[0] = sum[0].add(objectMapper.readValue(Objects.requireNonNull(result.getBody()).substring(1, result.getBody().length() - 1), BigDecimal.class));
                } catch (RuntimeException | URISyntaxException | JsonProcessingException ignored) {
                }
            });

            return sum[0];
        } else {
            return BigDecimal.valueOf(0);
        }

    }

    @Override
    public Iterable<Order> getAll(String username, String password) {
        return orderDao.findAll();
    }

    @Override
    public Iterable<Order> getAllByBookName(String username, String password, String bookName) {
        Iterable<OrderItem> orderItems = orderItemDao.findAllByBook_Name(bookName);
        HashSet<Order> orderHashMap = new HashSet<>();

        orderItems.forEach(orderItem -> {
            if (!orderItem.isActivated()) {
                orderHashMap.add(orderItem.getOrder());
            }
        });
        return orderHashMap;
    }

    @Override
    public Iterable<Order> getAllByTimePeriod(String username, String password, Date start, Date end) {
        return orderDao.findOrdersByPeriod(start, end);
    }

    @Override
    public Iterable<Order> getOrdersByUser_UsernameAndUser_PasswordAndBook_NameAndTimePeriod(String username, String password, Date start, Date end) {
        return orderDao.findOrdersByPeriodAndUser_UsernameAndUser_Password(username, password, start, end);
    }


}
