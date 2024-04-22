package com.example.restaurant.web;


import com.example.restaurant.entity.EmailAuth;
import com.example.restaurant.entity.PhoneAuth;
import com.example.restaurant.service.EmailService;
import com.example.restaurant.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {


  @Autowired
  EmailService emailService;


  @Autowired
  SmsService smsService;
  @PostMapping("/send-verify-email")
  public ResponseEntity<CompletableFuture<Boolean>> send_vertiy(@RequestBody String email ) throws Exception {

    return ResponseEntity.ok(emailService.sendAuthenticationCode((java.lang.String) email));

  }

  @PostMapping("/send-verify-phone")
  public  ResponseEntity<CompletableFuture<Boolean>> send_vertiy_phone(@RequestBody PhoneAuth phoneAuth ) throws Exception {

    return ResponseEntity.ok((smsService.send_message((java.lang.String) phoneAuth.getPhone())));


  }


  @PostMapping("/verify-check-email")
  public  ResponseEntity<HashMap<String,Object>> auth_email_check(@RequestBody EmailAuth emailauth) throws Exception {

    return ResponseEntity.ok((emailService.checkAuthentication(emailauth.getEmail(),emailauth.getAuth())));

  }

  @PostMapping("/verify-check-phone")
  public  ResponseEntity<HashMap<String,Object>> auth_phone_check(@RequestBody PhoneAuth phoneAuth) throws Exception {

    return ResponseEntity.ok((smsService.checkAuthentication(phoneAuth.getPhone(),phoneAuth.getAuth())));

  }
}
