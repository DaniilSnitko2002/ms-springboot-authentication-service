package com.springboot.msauthenticationservice.configuration;

import com.sun.jdi.InternalException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.lang.module.FindException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * App Exception Handler
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception ex){
        ProblemDetail errorDetail = null;

        if(ex instanceof BadCredentialsException){
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
            errorDetail.setProperty("Access denied", "Authentication failure");
            logger.error("Unauthorized error: {}", errorDetail.getDetail());
        }

        if(ex instanceof FindException){
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(302), ex.getMessage());
            errorDetail.setProperty("Registration denied", "User already exists");
            logger.error("Error: {}", errorDetail.getDetail());
        }

        if(ex instanceof InternalException){
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), ex.getMessage());
            errorDetail.setProperty("Failure", "Internal Server Error");
            logger.error("Error: {}", errorDetail.getDetail());
        }

        return errorDetail;
    }
}
