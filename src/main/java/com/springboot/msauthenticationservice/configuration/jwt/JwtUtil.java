package com.springboot.msauthenticationservice.configuration.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Component
public class JwtUtil {
    /**
     * The secret key
     */
    private static final String SECRET_KEY = "TheSecretDeyThatShouldBeLongEnoughToBeAtLeast256bits";

    /**
     * Time a token will be valid before it expires
     */
    private static final long EXPIRATION_TIME = 86400000; // 1 day

    /**
     * Method to generate the token just from the user details
     * @param userDetails the user details
     * @return the token
     */
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Method for the token generation
     * @param extraClaims the extra claims that we want to add
     * @param userDetails the user details
     * @return the token
     */
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Method to extract the username
     * @param token the token
     * @return the username
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Method to extract a single claim
     * @param token the token
     * @param claimsResolver the claim resolver
     * @return a claim
     * @param <T>
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extract all the claims
     * @param token the token
     * @return all the Claims
     */
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

    /**
     * Method to get the Sign in Key
     * @return the sign in key
     */
    private static Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Method to validate the token
     * @param token the token
     * @param userDetails the user details
     * @return a boolean
     */
    public boolean isTokenValid(String token, UserDetails userDetails){
            final String username = extractUsername(token);
            return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    /**
     * Method to check if a token is expired
     * @param token the token
     * @return a boolean
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Method to extract the expiration date
     * @param token the token
     * @return a date
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    }

