package com.springboot.msauthenticationservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogInDto {

    /**
     * The email
     */
    @Email(message = "should be of type email")
    @NotNull
    private String email;

    /**
     * The password
     */
    @NotNull
    private String password;

    /**
     * The token
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String token;

}
