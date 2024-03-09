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
public class SignUpDto {

    /**
     * The id
     */
    private UUID id;

    /**
     * The username
     */
    @NotBlank
    private String username;

    /**
     * The email
     */
    @Email(message = "should be of type email")
    @NotBlank
    private String email;

    /**
     * The password
     */
    @NotBlank
    private String password;
}
