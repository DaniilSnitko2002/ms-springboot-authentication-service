package com.springboot.msauthenticationservice.service.impl;

import com.springboot.msauthenticationservice.dto.LogInDto;
import com.springboot.msauthenticationservice.dto.SignUpDto;
import com.springboot.msauthenticationservice.entity.User;
import com.springboot.msauthenticationservice.repository.UserRepository;
import com.springboot.msauthenticationservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     *
     * @param userRepository The user repository
     */
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public String signUpUserView(SignUpDto signUpDto) throws IOException {
        return "Hi there, I'm currently signing up";
    }

    @Override
    public String logInUserView(LogInDto logInDto) throws IOException {
        Optional<User> value = userRepository.findLoggingUser(logInDto.email, logInDto.password);
        return value.toString();
    }
}
