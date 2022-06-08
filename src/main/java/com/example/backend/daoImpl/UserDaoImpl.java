package com.example.backend.daoImpl;

import com.example.backend.dao.UserDao;
import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    UserRepository userRepository;

    public Optional<User> findUserByUsernameAndPassword(String username, String password) {
        return userRepository.findUserByUsernameAndPassword(username, password);
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
