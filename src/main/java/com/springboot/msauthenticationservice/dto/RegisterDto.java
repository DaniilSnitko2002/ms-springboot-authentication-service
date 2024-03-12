package com.springboot.msauthenticationservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {


    /**
     * The id
     */
    private UUID id;

    /**
     * The username
     */
    @NotBlank(message = "Username is mandatory")
    private String username;

    /**
     * The email
     */
    @Email(message = "Should be of type email")
    @NotBlank(message = "Email is mandatory")
    private String email;

    /**
     * The password
     */
    @NotBlank(message = "Password is mandatory")
    private String password;
}
