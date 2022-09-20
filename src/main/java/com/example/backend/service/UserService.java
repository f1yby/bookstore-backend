package com.example.backend.service;

import com.example.backend.dto.UserWithBuyAmount;

import java.sql.Date;
import java.util.Optional;

public interface UserService {
    String add(String username, String password, String email);

    String setPermission(String adminUserName, String adminPassword, Integer uid, String Permission);

    Optional<String> getPermissionByUsernameAndPassword(String username, String password);

    Iterable<UserWithBuyAmount> findAll(String username, String password);

    Optional<UserWithBuyAmount> findUserByUsername(String admin, String password, String username);

    Iterable<UserWithBuyAmount> findAllByTimePeriod(String username, String password, Date valueOf, Date valueOf1);
}
