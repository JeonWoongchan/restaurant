package com.example.restaurant.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
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

  @Getter
  @Value("${jwt.accessTokenExpirationMinutes}") // application.properties 또는 application.yml에 설정된 액세스 토큰 만료 시간(분)
  private int accessTokenExpirationMinutes;

  @Getter
  @Value("${jwt.refreshTokenExpirationMinutes}") // application.properties 또는 application.yml에 설정된 리프레시 토큰 만료 시간(분)
  private int refreshTokenExpirationMinutes;

  // 액세스 토큰 생성
  public String generateAccessToken(String email) {
    LocalDateTime currentTime = LocalDateTime.now();
    LocalDateTime expirationTime = currentTime.plusMinutes(accessTokenExpirationMinutes);

    return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
            .setExpiration(Date.from(expirationTime.atZone(ZoneId.systemDefault()).toInstant()))
            .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
            .compact();
  }

  // 리프레시 토큰 생성
  public String generateRefreshToken(String email) {
    LocalDateTime currentTime = LocalDateTime.now();
    LocalDateTime expirationTime = currentTime.plusMinutes(refreshTokenExpirationMinutes);

    return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
            .setExpiration(Date.from(expirationTime.atZone(ZoneId.systemDefault()).toInstant()))
            .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
            .compact();
  }

  // 액세스 토큰 생성 및 반환
  public String generateAccessTokenWrapper(String email) {
    return generateAccessToken(email);
  }

  // 리프레시 토큰 생성 및 반환
  public String generateRefreshTokenWrapper(String email) {
    return generateRefreshToken(email);
  }

  // 토큰에서 이메일 추출
  public String extractEmailFromToken(String token) {
    try {
      Claims claims = Jwts.parserBuilder()
              .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
              .build()
              .parseClaimsJws(token)
              .getBody();

      return claims.getSubject();
    } catch (Exception e) {
      return null;
    }
  }

  // 리프레시 토큰의 유효성 확인
  public boolean isValidRefreshToken(String refreshToken) {
    try {
      Jwts.parserBuilder()
              .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
              .build()
              .parseClaimsJws(refreshToken);

      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
