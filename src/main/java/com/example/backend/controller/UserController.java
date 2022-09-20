package com.example.backend.controller;

import com.example.backend.dto.UserWithBuyAmount;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://jiarui.omen15:8000")
    @PostMapping(path = "/add")
    public @ResponseBody String addUser(@RequestParam String username, @RequestParam String password, @RequestParam String email) {
        return userService.add(username, password, email);
    }

    @CrossOrigin(origins = "http://jiarui.omen15:8000")
    @PostMapping(path = "/getPermissionByUsernameAndPassword")
    public @ResponseBody Optional<String> getUserPrivilegeByUsernameAndPassword(@RequestParam String username, @RequestParam String password) {
        return userService.getPermissionByUsernameAndPassword(username, password);
    }


    @CrossOrigin(origins = "http://jiarui.omen15:8000")
    @PostMapping(path = "/admin/setPermission")
    public @ResponseBody String setPermission(@RequestParam String username, @RequestParam String password, @RequestParam Integer uid, @RequestParam String permission) {
        return userService.setPermission(username, password, uid, permission);
    }

    @CrossOrigin(origins = "http://jiarui.omen15:8000")
    @PostMapping(path = "/admin/findAll")
    public @ResponseBody Iterable<UserWithBuyAmount> findAll(@RequestParam String username, @RequestParam String password) {
        return userService.findAll(username, password);
    }

    @CrossOrigin(origins = "http://jiarui.omen15:8000")
    @PostMapping(path = "/admin/findAllByTimePeriod")
    public @ResponseBody Iterable<UserWithBuyAmount> findAll(@RequestParam String username, @RequestParam String password, @RequestParam String start, @RequestParam String end) {
        return userService.findAllByTimePeriod(username, password, Date.valueOf(start),Date.valueOf(end));
    }

    @CrossOrigin(origins = "http://jiarui.omen15:8000")
    @PostMapping(path = "/admin/findByUsername")
    public @ResponseBody Optional<UserWithBuyAmount> findByUsername(@RequestParam String admin, @RequestParam String password, @RequestParam String username) {
        return userService.findUserByUsername(admin, password, username);
    }
}
