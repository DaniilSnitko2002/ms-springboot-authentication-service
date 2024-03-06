package com.springboot.msauthenticationservice.mapper;

import com.springboot.msauthenticationservice.dto.LogInDto;
import com.springboot.msauthenticationservice.dto.SignUpDto;
import com.springboot.msauthenticationservice.entity.User;

public interface UserMapper {

    /**
     * Map SignUpDto
     * @param user the user
     * @return the SignUpDeto
     */
    SignUpDto mapSignUp(User user);

    /**
     * Map User
     * @param signUpDto the SignUpDto
     * @return the user
     */
    User mapSignUp(SignUpDto signUpDto);


    /**
     * Map LogInDto
     * @param user the user
     * @return the LogInDto
     */
    LogInDto mapLogIn(User user);

}
