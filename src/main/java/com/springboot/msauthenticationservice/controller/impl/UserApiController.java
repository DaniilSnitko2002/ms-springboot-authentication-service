package com.springboot.msauthenticationservice.controller.impl;

import com.springboot.msauthenticationservice.controller.UserApi;
import com.springboot.msauthenticationservice.dto.LogInDto;
import com.springboot.msauthenticationservice.dto.SignUpDto;
import com.springboot.msauthenticationservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

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

    @Override
    public ResponseEntity<String> signUpUserView(SignUpDto signUpDto) {
        try {

            var value = this.userService.signUpUserView(signUpDto);
            return ResponseEntity.ok(value);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> logInUserView(LogInDto logInDto) {
        try {
            var value = this.userService.logInUserView(logInDto);

            return ResponseEntity.ok(value);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
