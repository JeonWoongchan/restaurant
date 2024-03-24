package com.example.restaurant.web;


import com.example.restaurant.dto.CustomerDto;
import com.example.restaurant.entity.Customer;
import com.example.restaurant.repository.CustomerRepository;
//import com.example.restaurant.service.CustomerService;
import com.example.restaurant.service.AuthService;
import com.example.restaurant.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@CrossOrigin(origins = "*")

@RestController()
@RequestMapping("/login")
@RequiredArgsConstructor
public class CustomerController {

    @Autowired  CustomerRepository customerRepository;

    @Autowired private final CustomerService customerService;

    @Autowired
    AuthService authService;


    // 로그인
    @PostMapping("/sign-in")
    public ResponseEntity<HashMap<String, Optional<String>>>login(@RequestBody CustomerDto dto, HttpSession session)
    {

        return ResponseEntity.ok(customerService.login(dto.getEmail(), dto.getPassword(), session));

    }


    @PostMapping("/refreshToken")
    public ResponseEntity<HashMap<String, Optional<String>>> refreshAccessToken(@RequestBody HashMap<String, String> requestData,HttpSession session) {
        String refreshToken = requestData.get("refreshToken");

        HashMap<String, Optional<String>> response = customerService.refreshAccessToken(refreshToken,session);
        if (response.containsKey("status") && response.get("status").get().equals("-2")) {
            // 리프레시 토큰이 유효하지 않은 경우
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Integer> signup(@RequestBody Customer customer) throws Exception {

        return ResponseEntity.ok(customerService.joinCustomer(customer));

    }

    @PostMapping("/sign-up/duplicate")
    public ResponseEntity<Boolean> duplicate(@RequestBody CustomerDto dto) throws Exception {

        return ResponseEntity.ok(customerService.duplicateCustomer(dto.getEmail()));

    }

    @PostMapping("/login-check")
    public String myEndpoint(@RequestHeader("Authorization") String authorizationHeader,
                             @RequestHeader("Content-Type") String contentTypeHeader) {
        // 헤더 값에 따라 다른 작업을 수행할 수 있습니다.

        if(authorizationHeader.equals("Bearer null") || authorizationHeader.equals("Bearer undefined")){
            return "-1";
        }else{
            return "Received Authorization header: " + authorizationHeader +
                    ", Content-Type header: " + contentTypeHeader;
        }

    }

}

