package com.springboot.msauthenticationservice.controller.impl;

import com.springboot.msauthenticationservice.controller.UserApi;
import com.springboot.msauthenticationservice.dto.LogInDto;
import com.springboot.msauthenticationservice.dto.SignUpDto;
import com.springboot.msauthenticationservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

/**
 * The Api User
 */
@RestController
@Slf4j
public class UserApiController implements UserApi {

    private final UserService userService;

    /**
     * Constructor
     * @param userService instance
     */
    public UserApiController(UserService userService){
        this.userService = userService;
    }

    /**
     * Signing up a user
     * @param signUpDto the dto
     * @return the response entity
     */
    @Override
    public ResponseEntity<String> signUpUserView(SignUpDto signUpDto) {
            var savedUser = this.userService.signUpUser(signUpDto);
            return ResponseEntity.created(URI.create(
                            "/user/".concat(savedUser.getId().toString())))
                    .contentType(MediaType.APPLICATION_JSON).body("Id: ".concat(savedUser.getId().toString()));

    }

    /**
     * Logging a user
     * @param logInDto the dto
     * @return the response entity
     */
    @Override
    public ResponseEntity<LogInDto> logInUserView(LogInDto logInDto) {
            var value = this.userService.logInUser(logInDto);
            return ResponseEntity.ok(value);

    }
}
