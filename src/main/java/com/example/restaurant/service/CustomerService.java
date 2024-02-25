package com.example.restaurant.service;

import com.example.restaurant.dto.CustomerDto;
import com.example.restaurant.entity.Customer;
import com.example.restaurant.repository.CustomerRepository;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Optional;
import java.util.List;
import java.util.Random;

@Service
public class CustomerService {


  @Autowired
   CustomerRepository customerRepository;

  @Autowired
  EmailService emailService;



  // 회원 가입
  public String joinCustomer(CustomerDto dto, Customer customer) throws Exception {
        customerRepository.save(customer);
        return customer.getUsername() + "님 회원가입 성공하셨습니다"; // Assuming Customer has an ID field
  }

  // 중복 체크
  public boolean duplicateCustomer(String email) {
    List<Customer> customers = customerRepository.findByEmail(email);
    DefaultMessageService messageService =  NurigoApp.INSTANCE.initialize("API 키 입력", "API 시크릿 키 입력", "https://api.coolsms.co.kr");
    // Message 패키지가 중복될 경우 net.nurigo.sdk.message.model.Message로 치환하여 주세요

    return customers.isEmpty();
  }

  // 로그인
  public int login(String email, String password) {
    Optional<Customer> dbpassword = customerRepository.login(email);

    if (dbpassword.isEmpty()) {

      return -1; // 아이디가 없는 경우

    } else if (!password.equals(dbpassword.get().getPassword())) {

      return 0; // 비밀번호 불일치
    } else {
      return 1; // 로그인 성공
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

