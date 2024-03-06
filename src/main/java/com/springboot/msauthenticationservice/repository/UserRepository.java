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
     * @param email the email
     * @param password the password
     * @return the optional
     */
    Optional<User> findByEmailIgnoreCaseAndPassword(String email, String password);

    /**
     * Find a user by email
     * @param email the email
     * @return the optional
     */
    Optional<User> findByEmail(String email);
}
