package com.springboot.msauthenticationservice.service.impl;

import com.springboot.msauthenticationservice.constant.PrefixPasswordConstant;
import com.springboot.msauthenticationservice.constant.UserRoles;
import com.springboot.msauthenticationservice.dto.LogInDto;
import com.springboot.msauthenticationservice.dto.SignUpDto;
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
     * Signing up a user
     * @param signUpDto the SignUpDto
     * @return the SignUpDto
     */
    @Override
    public SignUpDto signUpUser(SignUpDto signUpDto) {
        var password = PrefixPasswordConstant.PASSWORD_PREFIX.concat(signUpDto.getPassword());

        var user = User.builder()
                .name(signUpDto.getUsername())
                .email(signUpDto.getEmail())
                .password(password)
                .role(UserRoles.USER)
                .build();

        return Optional.of(user)
                .map(this::validateAlreadyExists)
                .map(this::encodePassword)
                .map(userRepository::save)
                .map(userMapper::mapSignUp)
                .orElseThrow(InternalException::new);

    }

    /**
     * Logging a user
     * @param logInDto the LogInDto
     * @return the LogInDto
     */
    @Override
    public LogInDto logInUser(LogInDto logInDto) {
        var password = PrefixPasswordConstant.PASSWORD_PREFIX.concat(logInDto.getPassword());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        logInDto.getEmail(),
                        password)
                );

        var user = userRepository.
                findByEmail(logInDto.getEmail())
                .orElseThrow();

        var jwtToken = jwtUtil.generateToken(user);

        return LogInDto.builder().token(jwtToken).build();
    }

    /**
     * Validation if a user already exists
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
     * Encode the password
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
