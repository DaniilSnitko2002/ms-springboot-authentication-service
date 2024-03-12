package com.springboot.msauthenticationservice.controller;

import com.springboot.msauthenticationservice.dto.AuthenticationDto;
import com.springboot.msauthenticationservice.dto.RegisterDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/user")
public interface UserApi {

    /*
     * Endpoint to register a user
     * @Return user id
     */
    @Operation(summary = "Register a user", description = "Register a user in the application", tags = {"User"})
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Successful registration"),
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "500", description = "Internal server Error"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "405", description = "Method Not Allowed")
    })
    @PostMapping(value = "/sign_up", produces = {"application/json"})
    ResponseEntity<String> registerUser(@Valid @RequestBody RegisterDto registerDto);

    /*
     * Endpoint to authenticate a user
     * @Return Response entity
     */
    @Operation(summary = "Authenticate a user", description = "Authenticate a user in the application", tags = {"User"})
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Successful authentication"),
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "500", description = "Internal server Error"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "405", description = "Method Not Allowed")
    })
    @PostMapping(value = "/log_in", produces = {"application/json"})
    ResponseEntity<String> authenticateUser(@Valid @RequestBody AuthenticationDto authenticationDto);
}
