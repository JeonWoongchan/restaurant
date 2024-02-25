package com.example.restaurant.service;


import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import jakarta.mail.internet.MimeMessage;


import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.security.SecureRandom;
import java.util.concurrent.CompletableFuture;


@Slf4j
@RequiredArgsConstructor
@Service
public class EmailService {

  private final JavaMailSender emailSender;
  private final SpringTemplateEngine templateEngine;


  @Async
  public String sendAuthenticationCode (String semail) throws Exception {
    String Authenti = emailcreateCode(); // 인증코드 생성
    String email = "hazekoi05@gmail.com";



    MimeMessage mimeMessage = emailSender.createMimeMessage();

    mimeMessage.addRecipients(MimeMessage.RecipientType.TO,email ); // 보낼 이메일 설정
    mimeMessage.setSubject("인증 코드 메일 입니다" ); // 이메일 제목
    mimeMessage.setText(setContext(Authenti,email), "utf-8", "html"); // 내용 설정(Template Process)

    // 보낼 때 이름 설정하고 싶은 경우
    // mimeMessage.setFrom(new InternetAddress("laptopgyutti28@gmail.com", "김규식"));

    emailSender.send(mimeMessage); // 이메일 전송
    CompletableFuture.completedFuture(email);

    return Authenti;
  }

  private String setContext(String Authenti, String email) throws Exception {
    // 타임리프 설정하는 코드



    Context context = new Context();
    context.setVariable("Authenti", Authenti); // Template에 전달할 데이터 설정
    context.setVariable("email",email);
    return templateEngine.process("email.html", context); // email.html
  }

  private String emailcreateCode() {
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    SecureRandom random = new SecureRandom();
    StringBuilder sb = new StringBuilder(6);
    String Authenti = "";
    for (int i = 0; i < 6; i++) {
      int index = random.nextInt(characters.length());
      sb.append(characters.charAt(index));
    }
    Authenti = sb.toString();
    return Authenti;
  }


  public boolean checkAuthentication(String email, String inputAuth, String username) throws Exception {
    String Authenti = sendAuthenticationCode(email);

    if (Authenti.equals(inputAuth)) {
      return true;
    } else {
      return false;
    }


  }
}
