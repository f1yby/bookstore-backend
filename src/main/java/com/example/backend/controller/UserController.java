package com.example.backend.controller;

import com.example.backend.entity.Book;
import com.example.backend.entity.User;
import com.example.backend.entity.UserPrivilege;
import com.example.backend.repository.BookRepository;
import com.example.backend.repository.UserPrivilegeRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    private UserPrivilegeRepository userPrivilegeRepository;

    @CrossOrigin(origins = "http://jiarui.omen15:8000")
    @PostMapping(path = "/addUser")
    public @ResponseBody String addUser(@RequestParam String username, @RequestParam String password) {
        Optional<UserPrivilege> optionalUserPrivilege = userRepository.getUserPrivilegeByUsernameAndPassword(username, password);
        if (!optionalUserPrivilege.isPresent()) {
            Optional<UserPrivilege> optionalNormalPrivilege = userPrivilegeRepository.getByPrivilege("Normal");
            if (optionalNormalPrivilege.isPresent()) {
                User user = new User();
                user.setPassword(password);
                user.setUsername(username);
                user.setUserPrivilege(optionalNormalPrivilege.get());
                userRepository.save(user);
                return "Ok";
            }

        }
        return "Err";
    }

    @CrossOrigin(origins = "http://jiarui.omen15:8000")
    @PostMapping(path = "/getUserPrivilegeByUsernameAndPassword")
    public @ResponseBody Optional<UserPrivilege> getUserPrivilegeByUsernameAndPassword(@RequestParam String username, @RequestParam String password) {
        return userRepository.getUserPrivilegeByUsernameAndPassword(username, password);
    }
}
