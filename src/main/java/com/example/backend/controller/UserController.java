package com.example.backend.controller;

import com.example.backend.entity.User;
import com.example.backend.entity.UserPrivilege;
import com.example.backend.repository.UserPrivilegeRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://jiarui.omen15:8000")
    @PostMapping(path = "/addUser")
    public @ResponseBody String addUser(@RequestParam String username, @RequestParam String password) {
        return userService.addUser(username, password);
    }

    @CrossOrigin(origins = "http://jiarui.omen15:8000")
    @PostMapping(path = "/getUserPrivilegeByUsernameAndPassword")
    public @ResponseBody Optional<String> getUserPrivilegeByUsernameAndPassword(@RequestParam String username, @RequestParam String password) {
        return userService.getUserPrivilegeByUsernameAndPassword(username, password);
    }
}
