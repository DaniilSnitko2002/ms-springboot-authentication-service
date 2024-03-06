package com.springboot.msauthenticationservice.repository;

import com.springboot.msauthenticationservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * User Repository
 */

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * Logging user
     * @param email
     * @param password
     * @return
     */
    Optional<User> findByEmailAndPassword(String email, String password);
}
