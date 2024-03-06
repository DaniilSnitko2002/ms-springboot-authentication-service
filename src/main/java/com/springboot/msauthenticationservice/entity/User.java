package com.springboot.msauthenticationservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users")
@Entity
public class User implements Serializable {
    /**
     * The id
     */
    @Id
    @GeneratedValue(generator = "USERS_ID_SEQ")
    @GenericGenerator(name = "USERS_ID_SEQ", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    /**
     * The name
     */
    @NotBlank(message = "Name is mandatory")
    @Column(name = "name")
    private String username;

    /**
     * The email
     */
    @NotBlank(message = "email is mandatory")
    private String email;

    /**
     * The password
     */
    @NotBlank(message = "password is mandatory")
    private String password;
}
