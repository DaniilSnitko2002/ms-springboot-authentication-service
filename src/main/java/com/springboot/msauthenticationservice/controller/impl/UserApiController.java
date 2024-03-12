package com.springboot.msauthenticationservice.controller.impl;

import com.springboot.msauthenticationservice.controller.UserApi;
import com.springboot.msauthenticationservice.dto.AuthenticationDto;
import com.springboot.msauthenticationservice.dto.RegisterDto;
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
     * Registering a user
     * @param registerDto the dto
     * @return the response entity
     */
    @Override
    public ResponseEntity<String> registerUser(RegisterDto registerDto) {
            var savedUser = this.userService.registerUser(registerDto);
            return ResponseEntity.created(URI.create(
                            "/user/".concat(savedUser.getId().toString())))
                    .contentType(MediaType.APPLICATION_JSON).body("id: ".concat(savedUser.getId().toString()));

    }

    /**
     * Logging a user
     * @param authenticationDto the dto
     * @return the response entity
     */
    @Override
    public ResponseEntity<String> authenticateUser(AuthenticationDto authenticationDto) {
            var user = this.userService.authenticateUser(authenticationDto);
            return ResponseEntity.ok(user.getToken());
    }
}
