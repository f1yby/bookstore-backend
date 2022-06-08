package com.example.backend.controller;

import com.example.backend.entity.*;
import com.example.backend.repository.*;
import com.example.backend.service.OrderService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(path = "/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @CrossOrigin(origins = "http://jiarui.omen15:8000")
    @PostMapping(path = "/checkOut")
    public @ResponseBody String checkOut(@RequestParam String username, @RequestParam String password, @RequestParam List<Integer> oiid) {
        return orderService.checkOut(username, password, oiid);
    }

    @CrossOrigin(origins = "http://jiarui.omen15:8000")
    @PostMapping(path = "/addToCart")
    public @ResponseBody String addToCart(@RequestParam String username, @RequestParam String password, @RequestParam Integer bid) {
        return orderService.addToCart(username, password, bid);
    }

    @CrossOrigin(origins = "http://jiarui.omen15:8000")
    @PostMapping(path = "/removeFromCart")
    public @ResponseBody String removeFromCart(@RequestParam String username, @RequestParam String password, @RequestParam Integer bid) {
        return orderService.removeFromCart(username, password, bid);
    }

    @CrossOrigin(origins = "http://jiarui.omen15:8000")
    @PostMapping(path = "/getCart")
    public @ResponseBody List<OrderItem> getCart(@RequestParam String username, @RequestParam String password) {
        return orderService.getCart(username, password);
    }

    @CrossOrigin(origins = "http://jiarui.omen15:8000")
    @PostMapping(path = "/getOrder")
    public @ResponseBody Iterable<Order> getOrder(@RequestParam String username, @RequestParam String password) {
        return orderService.getOrder(username, password);
    }


}
