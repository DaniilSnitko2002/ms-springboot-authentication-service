package com.springboot.msauthenticationservice.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

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
//    @GeneratedValue(generator = "USERS_ID_SEQ")
//    @GenericGenerator(name = "USERS_ID_SEQ", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private String id;

    /**
     * The name
     */
    @NotBlank(message = "Name is mandatory")
    private String name;

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
