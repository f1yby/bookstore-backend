package com.example.backend.repository;

import com.example.backend.entity.User;
import com.example.backend.entity.UserPrivilege;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Query("SELECT u.userPrivilege FROM User u WHERE u.username = :username and u.password = :password")
    Optional<UserPrivilege> getUserPrivilegeByUsernameAndPassword(String username, String password);
}