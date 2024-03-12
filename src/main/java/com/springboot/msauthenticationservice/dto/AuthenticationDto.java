package com.springboot.msauthenticationservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDto {

    /**
     * The email
     */
    @Email(message = "should be of type email")
    @NotBlank(message = "Email is mandatory")
    private String email;

    /**
     * The password
     */
    @NotBlank(message = "Password is mandatory")
    private String password;

    /**
     * The token
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String token;
}
