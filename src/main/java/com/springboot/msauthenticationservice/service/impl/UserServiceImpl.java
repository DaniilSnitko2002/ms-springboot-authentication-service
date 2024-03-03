package com.springboot.msauthenticationservice.service.impl;

import com.springboot.msauthenticationservice.dto.LogInDto;
import com.springboot.msauthenticationservice.dto.SignUpDto;
import com.springboot.msauthenticationservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Override
    public String signUpUserView(SignUpDto signUpDto) throws IOException {
        return "Hi there, I'm currently signing up";
    }

    @Override
    public String logInUserView(LogInDto logInDto) throws IOException {
        return "Hi there, I'm currently loggin";
    }
}
