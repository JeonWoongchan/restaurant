package com.example.restaurant.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

  @Scheduled(fixedRate = 300000) // 5분마다 실행
  public void initializeToken() {
    // 토큰 초기화 로직 구현
    // ...

    // 토큰 초기화 후 로그 출력
    System.out.println("토큰이 초기화되었습니다.");
  }
}