package com.example.demo.security;

import com.example.demo.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
private final long jwtExpiration = 86400000; // 24 hours

public String generateToken(Map<String, Object> claims, String subject) {
return Jwts.builder()
.setClaims(claims)
.setSubject(subject)
.setIssuedAt(new Date())
.setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
.signWith(key)
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
return extractClaim(token, claims -> claims.get("role", String.class));
}

public Long extractUserId(String token) {
return extractClaim(token, claims -> claims.get("userId", Long.class));
}

public boolean isTokenValid(String token, String username) {
try {
final String extractedUsername = extractUsername(token);
return extractedUsername.equals(username) && !isTokenExpired(token);
} catch (Exception e) {
return false;
}
}

public Jwt<?, ?> parseToken(String token) {
return Jwts.parserBuilder()
.setSigningKey(key)
.build()
.parse(token);
}

private <T> T extractClaim(String token, java.util.function.Function<Claims, T> claimsResolver) {
final Claims claims = extractAllClaims(token);
return claimsResolver.apply(claims);
}

private Claims extractAllClaims(String token) {
return Jwts.parserBuilder()
.setSigningKey(key)
.build()
.parseClaimsJws(token)
.getBody();
}

private boolean isTokenExpired(String token) {
return extractClaim(token, Claims::getExpiration).before(new Date());
}
}
