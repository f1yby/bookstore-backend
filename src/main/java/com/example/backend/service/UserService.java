package com.example.backend.service;

import com.example.backend.entity.User;
import com.example.backend.entity.UserPrivilege;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

public interface UserService {
    String addUser(String username, String password);

    Optional<String> getUserPrivilegeByUsernameAndPassword(String username, String password);
}
