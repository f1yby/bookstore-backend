package com.example.backend.dao;

import com.example.backend.entity.UserPrivilege;
import org.springframework.data.repository.CrudRepository;

public interface UserPrivilegeDao {

    UserPrivilege getUserPrivilegeByPrivilege(String privilege);
}