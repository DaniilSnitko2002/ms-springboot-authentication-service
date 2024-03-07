package com.springboot.msauthenticationservice.service.impl;

import com.springboot.msauthenticationservice.constant.PrefixPasswordConstant;
import com.springboot.msauthenticationservice.dto.LogInDto;
import com.springboot.msauthenticationservice.dto.SignUpDto;
import com.springboot.msauthenticationservice.mapper.UserMapper;
import com.springboot.msauthenticationservice.repository.UserRepository;
import com.springboot.msauthenticationservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

/**
 * The user service implementation
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor
     * @param userRepository the user repository
     * @param userMapper the user mapper
     */
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
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
                .map(this::encodePassword)
                .map(userMapper::mapSignUp)
                .map(userRepository::save)
                .map(userMapper::mapSignUp)
                .orElseThrow(()->
                    new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong when signing up the user"));
    }

    /**
     * Logging a user
     * @param logInDto the LogInDto
     * @return the LogInDto
     */
    @Override
    public LogInDto logInUser(LogInDto logInDto) {
        return userRepository.
                findByEmail(logInDto.getEmail())
                .map(userMapper::mapLogIn)
                .map(given ->{
                    verifyPassword(logInDto.getPassword(), given.getPassword());
                    return given;
                })
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
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email is already in use");
        });
        return signUpDto;
    }

    private SignUpDto encodePassword(SignUpDto signUpDto){
        var plainPassword = signUpDto.getPassword();
        var password = PrefixPasswordConstant.PASSWORD_PREFIX.concat(plainPassword);
        var encodedPassword = passwordEncoder.encode(password);
        signUpDto.setPassword(encodedPassword);
        return signUpDto;
    }

    private void verifyPassword(String logInPassword, String UserPassword){
        var password = PrefixPasswordConstant.PASSWORD_PREFIX.concat(logInPassword);

        boolean passwordMatches = passwordEncoder.matches(password, UserPassword);

        if(!passwordMatches){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect password");
        }
    }
}
