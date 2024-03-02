package com.example.restaurant.service;

import com.example.restaurant.entity.Customer;
import com.example.restaurant.repository.CustomerRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpSession;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.Base64;
import java.util.HashMap;
import java.util.Optional;
import java.util.List;

@Service
public class CustomerService {


  @Autowired
   CustomerRepository customerRepository;

  @Autowired
  EmailService emailService;



  // 회원 가입
  public String joinCustomer(Customer customer) throws Exception {
        customerRepository.save(customer);
        return customer.getUsername() + "님 회원가입 성공하셨습니다";
  }

  // 중복 체크
  public boolean duplicateCustomer(String email) {
    Optional<Customer> customer = customerRepository.findByEmail(email);
    DefaultMessageService messageService =  NurigoApp.INSTANCE.initialize("API 키 입력", "API 시크릿 키 입력", "https://api.coolsms.co.kr");
    // Message 패키지가 중복될 경우 net.nurigo.sdk.message.model.Message로 치환하여 주세요

    return customer.isEmpty();
  }

  // 로그인
  public HashMap<String,Optional<String>> login(String email, String password, HttpSession session) {
    Optional<Customer> dbpassword = customerRepository.login(email);
    HashMap<String,Optional<String>> log = new HashMap<String,Optional<String>>();
    if (dbpassword.isEmpty()) {


      log.put("status", Optional.of("-1"));


      return log; // 아이디가 없는 경우

    } else if (!password.equals(dbpassword.get().getPassword())) {

      log.put("status",Optional.of("0"));
      return log; // 비밀번호 불일치
    } else {


      log.put("status", Optional.of("1"));
      session.setAttribute("email", email);
      Optional<String> name = customerRepository.findByname(email);
      if (name.isPresent()) {
        log.put("name", name); // 이름 추가
      }

      byte[] secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded();

// 생성된 비밀키를 Base64 인코딩하여 저장
      String base64EncodedSecretKey = Base64.getEncoder().encodeToString(secretKey);

      // 토큰 생성
      String token = Jwts.builder()
              .setSubject(email)
              .signWith(SignatureAlgorithm.HS256, secretKey)
              .compact();
      log.put("token", Optional.of(token));
      return log;
    }
  }

  public Optional<Customer> selectMember(HttpSession session) {
    String email = (String) session.getAttribute("email");
    Optional<Customer>  customer = Optional.empty();
    if (email == null) {
      customer = customerRepository.findByEmail(email);
      return customer;
    } else {

      // 비회원
      return customer;
    }

  }



  //휴대폰번호 인증문자 보내기



public void message() {
  DefaultMessageService messageService =  NurigoApp.INSTANCE.initialize("NCSJC9B1CTYZFSM1", "QHBMBQLX6BBX8V8FEYX1VWUDR7OK4OYW", "https://api.coolsms.co.kr");
  // Message 패키지가 중복될 경우 net.nurigo.sdk.message.model.Message로 치환하여 주세요
  Message message = new Message();

  message.setFrom("01042609481");
  message.setTo("01042609481");
  message.setText("SMS는 한글 45자, 영자 90자까지 입력할 수 있습니다.");

  try {
    // send 메소드로 ArrayList<Message> 객체를 넣어도 동작합니다!
    messageService.send(message);
  } catch (NurigoMessageNotReceivedException exception) {
    // 발송에 실패한 메시지 목록을 확인할 수 있습니다!
    System.out.println(exception.getFailedMessageList());
    System.out.println(exception.getMessage());
  } catch (Exception exception) {
    System.out.println(exception.getMessage());
  }


}




}
