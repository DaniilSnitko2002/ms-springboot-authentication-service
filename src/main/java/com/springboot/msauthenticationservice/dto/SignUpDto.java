package com.springboot.msauthenticationservice.dto;

import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {

    /**
     * The id
     */
    private UUID id;
    /**
     * The username
     */

    @NotNull
    private String username;
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

    public UUID getId(){
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
