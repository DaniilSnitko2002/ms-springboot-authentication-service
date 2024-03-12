package com.springboot.msauthenticationservice.mapper;

import com.springboot.msauthenticationservice.dto.RegisterDto;
import com.springboot.msauthenticationservice.entity.User;

public interface UserMapper {

    /**
     * Map RegisterDto
     * @param user the user
     * @return the RegisterDto
     */
    RegisterDto mapRegister(User user);
}
