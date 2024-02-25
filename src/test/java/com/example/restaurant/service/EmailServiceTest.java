package com.example.restaurant.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmailServiceTest {



  @InjectMocks
  private EmailService emailService;

  @Test
  void sendVerificationCodeTest() {


    try {
//      emailService.sendAuthenticationCode();

      // 이메일이 성공적으로 전송되었는지 확인

    } catch (Exception e) {
      // 예외 처리
    }
  }

}
