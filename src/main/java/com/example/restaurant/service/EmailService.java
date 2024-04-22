package com.example.restaurant.service;

import com.example.restaurant.repository.mybatis.EmailAuthMapper;

import org.json.JSONObject;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import jakarta.mail.internet.MimeMessage;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailService {

  private final JavaMailSender emailSender;
  private final SpringTemplateEngine templateEngine;
  private final EmailAuthMapper emailAuthMapper;


  @Async

  public CompletableFuture<Boolean> sendAuthenticationCode(String email) throws Exception {
    // 입력받은 이메일을 JSON 객체로 변환하고, 이메일 주소를 추출합니다.
    JSONObject jsonObject = new JSONObject(email);
    String chemail = jsonObject.getString("email");

    // 인증 코드를 생성합니다.
    String authCode = emailCreateCode();

    // 이메일 주소의 인증 코드가 이미 존재하는지 확인하고, 존재하면 삭제합니다.
    int emailCount = emailAuthMapper.emailcount(chemail);
    if (emailCount >= 1) {
      emailAuthMapper.deleteauth(chemail);
    }

    // 이메일 전송을 위한 MimeMessage를 생성합니다.
    MimeMessage mimeMessage = emailSender.createMimeMessage();
    mimeMessage.addRecipients(MimeMessage.RecipientType.TO, chemail);
    mimeMessage.setSubject("인증 코드 메일입니다");
    mimeMessage.setText(setContext(authCode, chemail), "utf-8", "html");

    // 이메일을 전송합니다.
    try {
      emailSender.send(mimeMessage);

      // 이메일과 인증 코드를 데이터베이스에 저장합니다.
      emailAuthMapper.insertEmailAuth(chemail, authCode);

      // 5분 후에 인증 코드를 삭제하는 비동기 작업을 실행합니다.
      CompletableFuture.runAsync(() -> {
        try {
          Thread.sleep(5 * 60 * 1000); // 5분 대기
          deleteExpiredAuthCodes(chemail, authCode);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          e.printStackTrace();
        }
      });

      // 이메일 전송과 인증 코드 저장이 성공적으로 완료되었음을 나타내는 true를 반환합니다.
      return CompletableFuture.completedFuture(true);
    } catch (Exception e) {
      // 예외가 발생하면 false를 반환합니다.
      return CompletableFuture.completedFuture(false);
    }
  }

  private String setContext(String authCode, String email) {
    // 타임리프 설정 코드
    Context context = new Context();
    context.setVariable("authCode", authCode);
    context.setVariable("email", email);
    return templateEngine.process("email.html", context);
  }

  private String emailCreateCode() {
    // 인증 코드를 생성하는 코드
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    SecureRandom random = new SecureRandom();
    StringBuilder sb = new StringBuilder(6);
    for (int i = 0; i < 6; i++) {
      int index = random.nextInt(characters.length());
      sb.append(characters.charAt(index));
    }
    return sb.toString();
  }

  public HashMap<String, Object> checkAuthentication(String email, String auth) {
    // DB에서 저장된 인증 코드 가져오기

    HashMap<String, Object> map = new HashMap<String, Object>();
    HashMap<String, Object> mapsuccess = new HashMap<String, Object>();
    map.put("email", email);
    map.put("auth", auth);
    int success = emailAuthMapper.findAuthCodeByEmail(map);



    // 무한 루프


    if (success == 1) {
      mapsuccess.put("success", 1);
      deleteExpiredAuthCodes(email, auth);
    } else {
      mapsuccess.put("success", 0);
    }
    return mapsuccess;
    // 입력된 인증 코드와 DB에 저장된 코드 비교

  }

  // 매 5분마다 특정 이메일의 만료된 인증 코드를 삭제
  public void deleteExpiredAuthCodes(String email, String auth) {


    // 특정 이메일의 만료된 인증 코드를 삭제
    emailAuthMapper.deleteExpiredAuthCodes(email, auth);
  }
}
