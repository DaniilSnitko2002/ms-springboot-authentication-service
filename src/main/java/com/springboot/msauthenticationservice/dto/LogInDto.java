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
     * Only sent in the api REQUEST
     * The email
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Email(message = "should be of type email")
    @NotNull
    private String email;

    /**
     * Only sent in the api REQUEST
     * The password
     */
    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /**
     * Only sent in the api RESPONSE
     * The username
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String username;

    /**
     * Only sent in the api RESPONSE
     * The id
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UUID id;

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }
}
