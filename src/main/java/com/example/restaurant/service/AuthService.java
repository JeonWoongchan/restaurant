package com.example.restaurant.service;

import com.example.restaurant.repository.CustomerRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class AuthService {

  @Value("${jwt.secretKey}") // application.properties 또는 application.yml에 설정된 시크릿 키
  private String secretKey;

  @Value("${jwt.accessTokenExpirationMinutes}") // application.properties 또는 application.yml에 설정된 액세스 토큰 만료 시간(분)
  private int accessTokenExpirationMinutes;

  @Value("${jwt.refreshTokenExpirationMinutes}") // application.properties 또는 application.yml에 설정된 리프레시 토큰 만료 시간(분)
  private int refreshTokenExpirationMinutes;

  private final CustomerRepository customerRepository;

  public AuthService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  private String generateAccessToken(String email) {
    LocalDateTime currentTime = LocalDateTime.now();
    LocalDateTime expirationTime = currentTime.plusMinutes(accessTokenExpirationMinutes);

    return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
            .setExpiration(Date.from(expirationTime.atZone(ZoneId.systemDefault()).toInstant()))
            .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
            .compact();
  }

  private String generateRefreshToken(String email) {
    LocalDateTime currentTime = LocalDateTime.now();
    LocalDateTime expirationTime = currentTime.plusMinutes(refreshTokenExpirationMinutes);

    return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
            .setExpiration(Date.from(expirationTime.atZone(ZoneId.systemDefault()).toInstant()))
            .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
            .compact();
  }
  public String generateAccessTokenWrapper(String email) {
    return generateAccessToken(email);
  }

  public String generateRefreshTokenWrapper(String email) {
    return generateRefreshToken(email);
  }
}