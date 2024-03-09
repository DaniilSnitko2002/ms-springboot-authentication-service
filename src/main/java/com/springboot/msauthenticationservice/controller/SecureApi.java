package com.springboot.msauthenticationservice.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/secure")
public interface SecureApi {

    /*
     * Mock endpoint for security check
     * @Return String
     */
    @Operation(summary = "Say hello", description = "Saying hello", tags = {"Secure"})
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "500", description = "Internal server Error"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "405", description = "Method Not Allowed")
    })
    @GetMapping(value = "/hello", produces = {"application/json"})
    ResponseEntity<String> sayHello();
}
