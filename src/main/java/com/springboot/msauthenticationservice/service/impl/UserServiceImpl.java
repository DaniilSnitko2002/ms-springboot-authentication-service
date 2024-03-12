package com.springboot.msauthenticationservice.service.impl;

import com.springboot.msauthenticationservice.constant.PrefixPasswordConstant;
import com.springboot.msauthenticationservice.constant.UserRoles;
import com.springboot.msauthenticationservice.dto.AuthenticationDto;
import com.springboot.msauthenticationservice.dto.RegisterDto;
import com.springboot.msauthenticationservice.entity.User;
import com.springboot.msauthenticationservice.mapper.UserMapper;
import com.springboot.msauthenticationservice.repository.UserRepository;
import com.springboot.msauthenticationservice.configuration.jwt.JwtUtil;
import com.springboot.msauthenticationservice.service.UserService;
import com.sun.jdi.InternalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.module.FindException;
import java.util.Optional;

/**
 * The user service implementation
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;


    /**
     * Constructor
     * @param userRepository user repository
     * @param userMapper user mapper
     * @param passwordEncoder password encoder
     * @param jwtUtil jwt utils
     * @param authenticationManager authentication manager
     */
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Registering a user
     * @param registerDto the RegisterDto
     * @return the RegisterDto
     */
    @Override
    public RegisterDto registerUser(RegisterDto registerDto) {
        var password = PrefixPasswordConstant.PASSWORD_PREFIX.concat(registerDto.getPassword());

        var user = User.builder()
                .name(registerDto.getUsername())
                .email(registerDto.getEmail())
                .password(password)
                .role(UserRoles.USER)
                .build();

        return Optional.of(user)
                .map(this::validateAlreadyExists)
                .map(this::encodePassword)
                .map(userRepository::save)
                .map(userMapper::mapRegister)
                .orElseThrow(InternalException::new);

    }

    /**
     * Authentication of a user
     * @param authenticationDto the AuthenticationDto
     * @return the AuthenticationDto
     */
    @Override
    public AuthenticationDto authenticateUser(AuthenticationDto authenticationDto) {
        var password = PrefixPasswordConstant.PASSWORD_PREFIX.concat(authenticationDto.getPassword());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationDto.getEmail(),
                        password)
                );

        var user = userRepository.
                findByEmail(authenticationDto.getEmail())
                .orElseThrow();

        var jwtToken = jwtUtil.generateToken(user);

        return AuthenticationDto.builder().token(jwtToken).build();
    }

    /**
     * Method to check if a user already exists
     * @param user the user
     * @return the user
     */
    private User validateAlreadyExists(User user){
        userRepository.findByEmail(user.getEmail())
        .ifPresent(given ->{
            throw new FindException();
        });
        return user;
    }

    /**
     * Method for the password encoding
     * @param user user
     * @return user
     */
    private User encodePassword(User user){
        var password = user.getPassword();
        var encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        return user;
    }
}
