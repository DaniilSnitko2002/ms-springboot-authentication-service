package com.springboot.msauthenticationservice.mapper.impl;

import com.springboot.msauthenticationservice.dto.LogInDto;
import com.springboot.msauthenticationservice.dto.SignUpDto;
import com.springboot.msauthenticationservice.entity.User;
import com.springboot.msauthenticationservice.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public SignUpDto mapSignUp(User user) {
        if(user == null) {
            return null;
        }
        var signUpDto = new SignUpDto();
        BeanUtils.copyProperties(user,signUpDto);
        return signUpDto;
    }

    @Override
    public User mapSignUp(SignUpDto signUpDto) {
        if(signUpDto == null) {
            return null;
        }

        var user = new User();
        BeanUtils.copyProperties(signUpDto, user);
        return user;
    }

    @Override
    public LogInDto mapLogIn(User user) {
        if(user == null) {
            return null;
        }
        var logInDto = new LogInDto();
        BeanUtils.copyProperties(user,logInDto);
        return logInDto;
    }

}
