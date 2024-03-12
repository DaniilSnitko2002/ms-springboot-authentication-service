package com.springboot.msauthenticationservice.configuration;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.lang.module.FindException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

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
        ProblemDetail errorDetail;

        if(ex instanceof BadCredentialsException){
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
            errorDetail.setProperty("Failure", "Authentication failure");
            logger.error("Unauthorized error: {}", errorDetail.getDetail());
        }else if(ex instanceof FindException){
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(302), ex.getMessage());
            errorDetail.setProperty("Failure", "User already exists");
            logger.error("Error: {}", errorDetail.getDetail());
        }else if(ex instanceof MethodArgumentNotValidException){
            Map<String,String> errorMap = ((MethodArgumentNotValidException) ex).getAllErrors()
                    .stream()
                            .collect(Collectors.toMap(x ->((FieldError)x).getField(),
                                    DefaultMessageSourceResolvable::getDefaultMessage,
                                    (p,q) ->p, LinkedHashMap::new));
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(400), errorMap.toString());

            errorDetail.setProperty("Failure", "Bad Request");
            logger.error("Error: {}", errorDetail.getDetail());
        }else{
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), ex.getMessage());
            errorDetail.setProperty("Failure", "Internal Server Error");
            logger.error("Error: {}", errorDetail.getDetail());
        }

        return errorDetail;
    }
}
