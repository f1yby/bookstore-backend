package com.example.backend.service.Impl;

import com.example.backend.dao.UserDao;
import com.example.backend.dto.UserWithBuyAmount;
import com.example.backend.entity.User;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Optional;

@Service
@SessionScope
public class UserServiceImpl implements UserService {

    Time time;
    @Autowired
    private UserDao userDao;

    @Override
    public String add(String username, String password, String email) {
        Optional<User> optionalUser = userDao.findUserByUsername(username);
        if (!optionalUser.isPresent()) {
            User user = new User();
            user.setPassword(password);
            user.setUsername(username);
            user.setEmail(email);
            user.setPermission("Normal");
            userDao.save(user);
            return "Ok";
        }
        return "Duplicated User Name";
    }

    @Override
    public String setPermission(String adminUserName, String adminPassword, Integer uid, String permission) {
        Optional<User> optionalAdmin = userDao.findUserByUsernameAndPassword(adminUserName, adminPassword);
        if (optionalAdmin.isPresent() && optionalAdmin.get().getPermission().equals("Admin")) {
            Optional<User> optionalUser = userDao.findById(uid);
            if (optionalUser.isPresent() && !optionalUser.get().getPermission().equals("Admin")) {
                User user = optionalUser.get();
                user.setPermission(permission);
                userDao.save(user);
                return "OK";
            }
            return "User Not Found";
        }
        return "Invalid Admin Account";
    }

    @Override
    public Optional<String> getPermissionByUsernameAndPassword(String username, String password) {
        Optional<User> user = userDao.findUserByUsernameAndPassword(username, password);
        time = Time.valueOf(LocalTime.now());
        return user.map(User::getPermission);
    }

    @Override
    public Optional<String> logOut() {
        return Optional.of(String.valueOf(Time.valueOf(LocalTime.now()).getTime() - time.getTime()));
    }

    @Override
    public Iterable<UserWithBuyAmount> findAll(String username, String password) {
        Optional<User> user = userDao.findUserByUsernameAndPassword(username, password);
        if (user.isPresent() && user.get().getPermission().equals("Admin")) {
            LinkedList<UserWithBuyAmount> userWithBuyAmounts = new LinkedList<>();
            userDao.findAll().forEach(user1 -> userWithBuyAmounts.add(new UserWithBuyAmount(user1)));
            return userWithBuyAmounts;
        }
        return null;
    }

    @Override
    public Optional<UserWithBuyAmount> findUserByUsername(String admin, String password, String username) {
        Optional<User> user = userDao.findUserByUsernameAndPassword(admin, password);
        if (user.isPresent() && user.get().getPermission().equals("Admin")) {
            return userDao.findUserByUsername(username).map(UserWithBuyAmount::new);
        }
        return Optional.empty();
    }

    @Override
    public Iterable<UserWithBuyAmount> findAllByTimePeriod(String username, String password, Date start, Date end) {
        Optional<User> user = userDao.findUserByUsernameAndPassword(username, password);
        if (user.isPresent() && user.get().getPermission().equals("Admin")) {
            LinkedList<UserWithBuyAmount> userWithBuyAmounts = new LinkedList<>();
            userDao.findAll().forEach(user1 -> userWithBuyAmounts.add(new UserWithBuyAmount(user1, start, end)));
            return userWithBuyAmounts;
        }
        return null;
    }
}
