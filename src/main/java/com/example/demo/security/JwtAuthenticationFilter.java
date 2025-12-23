
package com.example.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

private final JwtUtil jwtUtil;

public JwtAuthenticationFilter(JwtUtil jwtUtil) {
this.jwtUtil = jwtUtil;
}

@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
throws ServletException, IOException {

final String authHeader = request.getHeader("Authorization");

if (authHeader == null || !authHeader.startsWith("Bearer ")) {
filterChain.doFilter(request, response);
return;
}

try {
final String jwt = authHeader.substring(7);
final String userEmail = jwtUtil.extractUsername(jwt);
final String role = jwtUtil.extractRole(jwt);
final Long userId = jwtUtil.extractUserId(jwt);

if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
if (jwtUtil.isTokenValid(jwt, userEmail)) {
UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
userEmail,
null,
Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role))
);
SecurityContextHolder.getContext().setAuthentication(authToken);
}
}
} catch (Exception e) {
// Invalid token, continue without authentication
}

filterChain.doFilter(request, response);
}
}