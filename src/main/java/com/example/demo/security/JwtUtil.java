package com.example.demo.security;

 

import com.example.demo.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import java.util.function.Function;

 

@Component

public class JwtUtil {

   

    private static final String SECRET = "mySecretKeymySecretKeymySecretKeymySecretKey";

    private static final int JWT_EXPIRATION = 86400000; // 24 hours

   

    private SecretKey getSigningKey() {

        return Keys.hmacShaKeyFor(SECRET.getBytes());

    }

   

    public String generateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder()

                .setClaims(claims)

                .setSubject(subject)

                .setIssuedAt(new Date(System.currentTimeMillis()))

                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))

                .signWith(getSigningKey())

                .compact();

    }

   

    public String generateTokenForUser(User user) {

        Map<String, Object> claims = new HashMap<>();

        claims.put("userId", user.getId());

        claims.put("email", user.getEmail());

        claims.put("role", user.getRole());

        return generateToken(claims, user.getEmail());

    }

   

    public String extractUsername(String token) {

        return extractClaim(token, Claims::getSubject);

    }

   

    public String extractRole(String token) {

        return extractClaim(token, claims -> (String) claims.get("role"));

    }

   

    public Long extractUserId(String token) {

        return extractClaim(token, claims -> {

            Object userId = claims.get("userId");

            if (userId instanceof Integer) {

                return ((Integer) userId).longValue();

            }

            return (Long) userId;

        });

    }

   

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {

        final Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);

    }

   

    private Claims extractAllClaims(String token) {

        return Jwts.parserBuilder()

                .setSigningKey(getSigningKey())

                .build()

                .parseClaimsJws(token)

                .getBody();

    }

   

    public boolean isTokenValid(String token, String username) {

        final String extractedUsername = extractUsername(token);

        return (extractedUsername.equals(username) && !isTokenExpired(token));

    }
    private boolean isTokenExpired(String token) {

        return extractExpiration(token).before(new Date());

    }
    private Date extractExpiration(String token) {

        return extractClaim(token, Claims::getExpiration);

    }
    public Jwt<?, ?> parseToken(String token) {

        return Jwts.parserBuilder()

                .setSigningKey(getSigningKey())

                .build()

                .parse(token);

    }

}