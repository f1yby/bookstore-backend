package com.example.backend.daoImpl;

import com.example.backend.dao.UserPrivilegeDao;
import com.example.backend.entity.UserPrivilege;
import com.example.backend.repository.UserPrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserPrivilegeDaoImpl implements UserPrivilegeDao {
    @Autowired
    UserPrivilegeRepository userPrivilegeRepository;

    @Override
    public UserPrivilege getUserPrivilegeByPrivilege(String privilege) {
        return userPrivilegeRepository.getUserPrivilegeByPrivilege(privilege);
    }
}
