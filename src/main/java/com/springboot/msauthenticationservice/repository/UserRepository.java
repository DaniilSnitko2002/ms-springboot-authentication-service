package com.springboot.msauthenticationservice.repository;

import com.springboot.msauthenticationservice.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * User Repository
 */
public interface UserRepository extends CrudRepository<User, String> {

    @Query(value = "SELECT u FROM User u" +
            "WHERE u.password LIKE :password" +
            "AND" +
            "WHERE u.email LIKE :email")
    Optional<User> findLoggingUser(String email, String password);
}
