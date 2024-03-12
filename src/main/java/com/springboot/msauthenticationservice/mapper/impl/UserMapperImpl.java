package com.springboot.msauthenticationservice.mapper.impl;

import com.springboot.msauthenticationservice.dto.RegisterDto;
import com.springboot.msauthenticationservice.entity.User;
import com.springboot.msauthenticationservice.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    /**
     * Map RegisterDto
     * @param user the user
     * @return the RegisterDto
     */
    @Override
    public RegisterDto mapRegister(User user) {
        if(user == null) {
            return null;
        }
        var registerDto = new RegisterDto();
        BeanUtils.copyProperties(user,registerDto);
        return registerDto;
    }
}
