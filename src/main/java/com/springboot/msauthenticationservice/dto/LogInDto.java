package com.springboot.msauthenticationservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;


@Data
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
     * The username
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String username;

    public String getUsername(){
        return this.username;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }
}
