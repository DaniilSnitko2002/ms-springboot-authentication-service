package com.springboot.msauthenticationservice.service;

import com.springboot.msauthenticationservice.dto.LogInDto;
import com.springboot.msauthenticationservice.dto.SignUpDto;

import java.io.IOException;

public interface UserService {
    String signUpUserView (SignUpDto signUpDto) throws IOException;

    String logInUserView (LogInDto logInDto) throws IOException;

}
