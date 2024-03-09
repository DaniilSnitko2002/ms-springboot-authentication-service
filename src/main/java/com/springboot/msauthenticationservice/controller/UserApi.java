package com.springboot.msauthenticationservice.controller;

import com.springboot.msauthenticationservice.dto.LogInDto;
import com.springboot.msauthenticationservice.dto.SignUpDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/user")
public interface UserApi {

    /*
     * Sign up user
     * @Return user id
     */
    @Operation(summary = "Sign Up User", description = "Signing up a user in the application", tags = {"User"})
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Successful registration"),
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "500", description = "Internal server Error"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "405", description = "Method Not Allowed")
    })
    @PostMapping(value = "/sign_up", produces = {"application/json"})
    ResponseEntity<String> signUpUser(@Valid @RequestBody SignUpDto signUpDto);

    /*
     * Log in user
     * @Return Response entity
     */
    @Operation(summary = "Log In User", description = "Log in a user in the application", tags = {"User"})
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Successful log in"),
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "500", description = "Internal server Error"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "405", description = "Method Not Allowed")
    })
    @PostMapping(value = "/log_in", produces = {"application/json"})
    ResponseEntity<String> logInUser(@Valid @RequestBody LogInDto logInDto);
}
