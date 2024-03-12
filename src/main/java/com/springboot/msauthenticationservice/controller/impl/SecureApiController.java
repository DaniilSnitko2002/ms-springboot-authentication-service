package com.springboot.msauthenticationservice.controller.impl;

import com.springboot.msauthenticationservice.controller.SecureApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SecureApiController implements SecureApi {

    /**
     * Mock for security check
     * @return String
     */
    @Override
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from secure endpoint");
    }
}
