package com.springboot.msauthenticationservice.service.impl;

import com.springboot.msauthenticationservice.dto.LogInDto;
import com.springboot.msauthenticationservice.dto.SignUpDto;
import com.springboot.msauthenticationservice.entity.User;
import com.springboot.msauthenticationservice.mapper.UserMapper;
import com.springboot.msauthenticationservice.repository.UserRepository;
import com.springboot.msauthenticationservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/**
 * The user service implementation
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    /**
     * Constructor
     * @param userRepository the user repository
     * @param userMapper the user mapper
     */
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    /**
     * Signing up a user
     * @param signUpDto the SignUpDto
     * @return the SignUpDto
     */
    @Override
    public SignUpDto signUpUser(SignUpDto signUpDto) {
        return Optional.of(signUpDto)
                .map(this::validateAlreadyExists)
                .map(userMapper::mapSignUp)
                .map(userRepository::save)
                .map(userMapper::mapSignUp)
                .orElseThrow(()->
                    new ResponseStatusException(HttpStatus.CONFLICT, "User already exists"));
    }

    /**
     * Logging a user
     * @param logInDto the LogInDto
     * @return the LogInDto
     */
    @Override
    public LogInDto logInUser(LogInDto logInDto) {
        return userRepository.
                findByEmailIgnoreCaseAndPassword(logInDto.getEmail(), logInDto.getPassword())
                .map(userMapper::mapLogIn)
                .orElseThrow( () ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    /**
     * Validation if a user already exists
     * @param signUpDto the SignUpDto
     * @return the SignUpDto
     */
    private SignUpDto validateAlreadyExists(SignUpDto signUpDto){
        userRepository.findByEmail(signUpDto.getEmail())
        .ifPresent(given ->{
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        });
        return signUpDto;
    }
}
