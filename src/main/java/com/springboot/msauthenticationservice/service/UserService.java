package com.springboot.msauthenticationservice.service;

import com.springboot.msauthenticationservice.dto.LogInDto;
import com.springboot.msauthenticationservice.dto.SignUpDto;

public interface UserService {
    SignUpDto signUpUser (SignUpDto signUpDto);

    LogInDto logInUser (LogInDto logInDto);

}
