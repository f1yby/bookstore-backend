package com.example.backend.service;

import com.example.backend.dto.UserWithBuyAmount;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;
@Service
public interface UserService {
    String add(String username, String password, String email);

    String setPermission(String adminUserName, String adminPassword, Integer uid, String Permission);

    Optional<String> getPermissionByUsernameAndPassword(String username, String password);

    Optional<String> logOut();

    Iterable<UserWithBuyAmount> findAll(String username, String password);

    Optional<UserWithBuyAmount> findUserByUsername(String admin, String password, String username);

    Iterable<UserWithBuyAmount> findAllByTimePeriod(String username, String password, Date valueOf, Date valueOf1);
}
