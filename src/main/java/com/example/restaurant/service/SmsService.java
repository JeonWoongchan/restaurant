package com.example.restaurant.service;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoEmptyResponseException;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.exception.NurigoUnknownException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class SmsService {





  public void message(String phoneNum) throws NurigoMessageNotReceivedException, NurigoEmptyResponseException, NurigoUnknownException {

    String code = phoneCode();
    DefaultMessageService messageService =  NurigoApp.INSTANCE.initialize("NCSJC9B1CTYZFSM1", "QHBMBQLX6BBX8V8FEYX1VWUDR7OK4OYW", "https://api.coolsms.co.kr");

    // Message 패키지가 중복될 경우 net.nurigo.sdk.message.model.Message로 치환하여 주세요
    Message message = new Message();

    message.setFrom("01042609481"); // 내번호
    message.setTo(phoneNum); // 상대방 법호
    message.setText(" [우디] 본인 확인 인증번호 ["+code+"]  인증코드 입니다.");

    messageService.send(message);
  }

  public String phoneCode() {
    String characters = "0123456789";

    SecureRandom random = new SecureRandom();

    StringBuilder sb = new StringBuilder(6);
    String pcode = "";
    for (int i = 0; i < 6; i++) {
      int index = random.nextInt(characters.length());
      sb.append(characters.charAt(index));
    }
    pcode = sb.toString();
    return pcode;
  }
}
