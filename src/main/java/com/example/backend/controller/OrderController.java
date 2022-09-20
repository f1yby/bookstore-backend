package com.example.backend.controller;

import com.example.backend.entity.Order;
import com.example.backend.entity.OrderItem;
import com.example.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @CrossOrigin(origins = "http://127.0.0.1:8000")
    @PostMapping(path = "/checkOut")
    public @ResponseBody String checkOut(@RequestParam String username, @RequestParam String password, @RequestParam List<Integer> oiid) {
        return orderService.checkOut(username, password, oiid);
    }

    @CrossOrigin(origins = "http://127.0.0.1:8000")
    @PostMapping(path = "/addToCart")
    public @ResponseBody String addToCart(@RequestParam String username, @RequestParam String password, @RequestParam Integer bid) {
        return orderService.addToCart(username, password, bid);
    }

    @CrossOrigin(origins = "http://127.0.0.1:8000")
    @PostMapping(path = "/removeFromCart")
    public @ResponseBody String removeFromCart(@RequestParam String username, @RequestParam String password, @RequestParam Integer bid) {
        return orderService.removeFromCart(username, password, bid);
    }

    @CrossOrigin(origins = "http://127.0.0.1:8000")
    @PostMapping(path = "/getCart")
    public @ResponseBody Iterable<OrderItem> getCart(@RequestParam String username, @RequestParam String password) {
        return orderService.getCart(username, password);
    }

    @CrossOrigin(origins = "http://127.0.0.1:8000")
    @PostMapping(path = "/getOrdersByUser_UsernameAndUser_Password")
    public @ResponseBody Iterable<Order> getOrdersByUser_UsernameAndUser_Password(@RequestParam String username, @RequestParam String password) {
        return orderService.getOrdersByUser_UsernameAndUser_Password(username, password);
    }

    @CrossOrigin(origins = "http://127.0.0.1:8000")
    @PostMapping(path = "/getOrdersByUser_UsernameAndUser_PasswordAndBook_Name")
    public @ResponseBody Iterable<Order> getOrdersByUser_UsernameAndUser_PasswordAnd_BookName(@RequestParam String username, @RequestParam String password, @RequestParam String bookName) {
        return orderService.getOrdersByUser_UsernameAndUser_PasswordAndBook_Name(username, password, bookName);
    }
    @CrossOrigin(origins = "http://127.0.0.1:8000")
    @PostMapping(path = "/getOrdersByUser_UsernameAndUser_PasswordAndOrder_Date")
    public @ResponseBody Iterable<Order> getOrdersByUser_UsernameAndUser_PasswordAnd_BookName(@RequestParam String username, @RequestParam String password, @RequestParam String start, @RequestParam String end) {
        return orderService.getOrdersByUser_UsernameAndUser_PasswordAndBook_NameAndTimePeriod(username, password, Date.valueOf(start), Date.valueOf(end));
    }


    @CrossOrigin(origins = "http://127.0.0.1:8000")
    @PostMapping(path = "/admin/getOrdersByUser_UsernameAndUser_PasswordAndOrder_Date")
    public @ResponseBody Iterable<OrderItem> getOrderItemsByUser_UsernameAndUser_PasswordAndOrder_Date(@RequestParam String username, @RequestParam String password, @RequestParam String date) {
        return orderService.findAllByUser_UsernameAndUser_PasswordAndActivatedAndOrder_Date(username, password, false, Date.valueOf(date));
    }

    @CrossOrigin(origins = "http://127.0.0.1:8000")
    @PostMapping(path = "/admin/findAll")
    public @ResponseBody Iterable<Order> getAll(@RequestParam String username, @RequestParam String password) {
        return orderService.getAll(username, password);
    }

    @CrossOrigin(origins = "http://127.0.0.1:8000")
    @PostMapping(path = "/admin/findAllByTimePeriod")
    public @ResponseBody Iterable<Order> getAllByTimePeriod(@RequestParam String username, @RequestParam String password, @RequestParam String start, @RequestParam String end) {
        return orderService.getAllByTimePeriod(username, password, Date.valueOf(start), Date.valueOf(end));
    }

    @CrossOrigin(origins = "http://127.0.0.1:8000")
    @PostMapping(path = "/admin/findAllByBookName")
    public @ResponseBody Iterable<Order> findAllByBookName(@RequestParam String username, @RequestParam String password, @RequestParam String bookName) {
        return orderService.getAllByBookName(username, password, bookName);
    }

}
