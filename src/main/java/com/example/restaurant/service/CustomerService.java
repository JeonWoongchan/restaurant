package com.example.restaurant.service;

import com.example.restaurant.dto.CustomerDto;
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

  @Autowired
  AuthService authService;

  // 회원 가입
  public String joinCustomer(Customer customer) throws Exception {
    customerRepository.save(customer);
    return customer.getUsername() + "님 회원가입 성공하셨습니다";
  }

  // 중복 체크
  public boolean duplicateCustomer(String email) {
    Optional<CustomerDto> customer = customerRepository.findE(email);
    DefaultMessageService messageService = NurigoApp.INSTANCE.initialize("API 키 입력", "API 시크릿 키 입력",
        "https://api.coolsms.co.kr");
    // Message 패키지가 중복될 경우 net.nurigo.sdk.message.model.Message로 치환하여 주세요

    return customer.isEmpty();
  }

  // 로그인
  public HashMap<String, Optional<String>> login(String email, String password, HttpSession session) {
    Optional<Customer> dbPassword = customerRepository.login(email);
    HashMap<String, Optional<String>> log = new HashMap<>();

    if (dbPassword.isEmpty()) {
      log.put("status", Optional.of("-1"));
      return log; // 아이디가 없는 경우
    } else if (!password.equals(dbPassword.get().getPassword())) {
      log.put("status", Optional.of("0"));
      return log; // 비밀번호 불일치
    } else {
      // 로그인 성공 시 액세스 토큰 및 리프레시 토큰 발급
      return issueTokens(email, session);
    }
  }

  // 액세스 토큰 및 리프레시 토큰 발급 메소드
  private HashMap<String, Optional<String>> issueTokens(String email, HttpSession session) {
    HashMap<String, Optional<String>> log = new HashMap<>();

    log.put("status", Optional.of("1"));
    session.setAttribute("email", email);
    Optional<String> name = customerRepository.findByname(email);
    name.ifPresent(n -> log.put("name", name)); // 이름 추가

    // 액세스 토큰 생성
    String accessToken = authService.generateAccessTokenWrapper(email);
    log.put("accessToken", Optional.of(accessToken));

    // 리프레시 토큰 생성
    String refreshToken = authService.generateRefreshTokenWrapper(email);
    log.put("refreshToken", Optional.of(refreshToken));

    String accessExpiration = String.valueOf(authService.getAccessTokenExpirationMinutes());
    String refreshExpiration = String.valueOf(authService.getRefreshTokenExpirationMinutes());

    log.put("accessTokenExpiration", Optional.of(accessExpiration));
    log.put("refreshTokenExpiration", Optional.of(refreshExpiration));

    return log;
  }

  public Optional<CustomerDto> selectMember(HttpSession session) {
    String email = (String) session.getAttribute("email");
    Optional<CustomerDto> customer = Optional.empty();
    if (email != null) {
      customer = customerRepository.findE(email);
      return customer;
    } else {

      // 비회원
      return customer;
    }

  }

  public Optional<Customer> findByIdMembmer(HttpSession session) {
    String email = (String) session.getAttribute("email");
    System.out.println(email);

    if (email != null) {
      return customerRepository.findByIdtoEmail(email);
    } else {
      // 비회원
      return Optional.empty();
    }
  }

  // 액세스 토큰 갱신 메소드
  public HashMap<String, Optional<String>> refreshAccessToken(String refreshToken, HttpSession session) {
    HashMap<String, Optional<String>> log = new HashMap<>();

    // 리프레시 토큰 검증 및 유효성 확인
    if (authService.isValidRefreshToken(refreshToken)) {
      String email = authService.extractEmailFromToken(refreshToken);
      if (email != null) {
        // 새로운 액세스 토큰 발급
        return issueTokens(email, session);
      } else {
        log.put("status", Optional.of("-1"));
        return log; // 이메일 추출 실패
      }
    } else {
      log.put("status", Optional.of("-2"));
      return log; // 유효하지 않은 리프레시 토큰
    }
  }

  // 휴대폰번호 인증문자 보내기

  public void message() {
    DefaultMessageService messageService = NurigoApp.INSTANCE.initialize("NCSJC9B1CTYZFSM1",
        "QHBMBQLX6BBX8V8FEYX1VWUDR7OK4OYW", "https://api.coolsms.co.kr");
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
