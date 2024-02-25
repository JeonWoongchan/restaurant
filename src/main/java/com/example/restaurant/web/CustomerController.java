//package com.example.restaurant.web;
//
//
//import com.example.restaurant.dto.CustomerDto;
//import com.example.restaurant.entity.Customer;
//import com.example.restaurant.repository.CustomerRepository;
////import com.example.restaurant.service.CustomerService;
//import jakarta.servlet.http.HttpSession;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//public class CustomerController {
//
//    CustomerRepository customerRepository;
//
//
//    CustomerService customerService;
//
//
//    // 로그인
//    @PostMapping("/sign-in")
//    public ResponseEntity<Integer> login(@RequestBody CustomerDto dto, HttpSession session)
//    {
//
//
//        return ResponseEntity.ok(customerService.login(dto.getEmail(), dto.getPassword()));
//
//    }
//    @PostMapping("/sign-up")
//    public ResponseEntity<String> signup(@RequestBody CustomerDto dto, Customer customer, HttpSession session) throws Exception {
//
//
//        return ResponseEntity.ok(customerService.joinCustomer(dto,customer));
//
//    }
//
//}
//
