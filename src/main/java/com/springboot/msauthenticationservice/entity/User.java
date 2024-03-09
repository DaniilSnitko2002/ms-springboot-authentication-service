package com.springboot.msauthenticationservice.entity;

import com.springboot.msauthenticationservice.constant.UserRoles;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * As a good practice it is recommended to have the User entity
 * extending the UserDetails of Springboot.
 * It makes the Authentication way more simple
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

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

    /**
     * The role
     */
    @Enumerated(EnumType.STRING)
    private UserRoles role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}













