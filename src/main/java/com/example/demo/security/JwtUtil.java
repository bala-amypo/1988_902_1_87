package com.example.demo.security;

import com.example.demo.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

@Value("${jwt.secret:mySecretKey}")
private String secret;

@Value("${jwt.expiration:86400000}")
private Long expiration;

public JwtUtil() {
// Default values for testing
if (this.secret == null) {
this.secret = "mySecretKeyThatIsLongEnoughForJWTHMACAlgorithmRequirements";
}
if (this.expiration == null) {
this.expiration = 86400000L;
}
}

private SecretKey getSigningKey() {
return Keys.hmacShaKeyFor(secret.getBytes());
}

public String generateToken(Map<String, Object> claims, String subject) {
return Jwts.builder()
.setClaims(claims)
.setSubject(subject)
.setIssuedAt(new Date())
.setExpiration(new Date(System.currentTimeMillis() + expiration))
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
return extractClaim(token, claims -> claims.get("role", String.class));
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

public <T> T extractClaim(String token, java.util.function.Function<Claims, T> claimsResolver) {
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

public JwtWrapper parseToken(String token) {
final Claims claims = extractAllClaims(token);
return new JwtWrapper(claims);
}

public static class JwtWrapper {
private final Claims payload;

public JwtWrapper(Claims payload) {
this.payload = payload;
}

public Claims getPayload() {
return payload;
}
}

public Claims extractAllClaimsForTest(String token) {
return extractAllClaims(token);
}
}
