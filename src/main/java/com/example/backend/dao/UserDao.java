package com.example.backend.dao;

import com.example.backend.entity.User;

import java.util.Optional;

public interface UserDao {
    Optional<User> findUserByUsernameAndPassword(String username, String password);

    Optional<User> findUserByUsername(String username);

    void save(User user);
}
