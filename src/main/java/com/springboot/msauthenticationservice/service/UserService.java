package com.springboot.msauthenticationservice.service;

import com.springboot.msauthenticationservice.dto.RegisterDto;
import com.springboot.msauthenticationservice.dto.AuthenticationDto;

public interface UserService {
    RegisterDto registerUser (RegisterDto registerDto);

    AuthenticationDto authenticateUser (AuthenticationDto authenticationDto);

}
