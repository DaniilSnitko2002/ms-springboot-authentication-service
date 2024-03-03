package com.springboot.msauthenticationservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Builder
@Getter
@Setter
@ToString
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
}
