package com.springboot.msauthenticationservice.service;

import com.springboot.msauthenticationservice.dto.LogInDto;
import com.springboot.msauthenticationservice.dto.SignUpDto;
import com.springboot.msauthenticationservice.entity.User;

import java.util.Optional;

public interface UserService {
    SignUpDto signUpUser (SignUpDto signUpDto);

    LogInDto logInUser (LogInDto logInDto);

}
