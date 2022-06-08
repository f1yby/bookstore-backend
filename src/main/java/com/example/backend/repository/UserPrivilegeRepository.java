package com.example.backend.repository;

import com.example.backend.entity.UserPrivilege;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserPrivilegeRepository extends CrudRepository<UserPrivilege, Integer> {

    UserPrivilege getUserPrivilegeByPrivilege(String privilege);
}