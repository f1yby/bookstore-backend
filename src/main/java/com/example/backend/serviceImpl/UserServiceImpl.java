package com.example.backend.serviceImpl;

import com.example.backend.dao.UserDao;
import com.example.backend.dao.UserPrivilegeDao;
import com.example.backend.entity.User;
import com.example.backend.entity.UserPrivilege;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserPrivilegeDao userPrivilegeDao;

    public  String addUser( String username, String password) {
        Optional<User> optionalUser = userDao.findUserByUsername(username);
        if (!optionalUser.isPresent()) {
            UserPrivilege normal = userPrivilegeDao.getUserPrivilegeByPrivilege("Normal");
            User user = new User();
            user.setPassword(password);
            user.setUsername(username);
            user.setUserPrivilege(normal);
            userDao.save(user);
            return "Ok";
        }
        return "Err";
    }

    public Optional<String> getUserPrivilegeByUsernameAndPassword( String username,  String password) {
        Optional<User> user = userDao.findUserByUsernameAndPassword(username, password);
        return user.map(u -> u.getUserPrivilege().getPrivilege());
    }
}
