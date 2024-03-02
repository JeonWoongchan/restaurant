package com.example.restaurant.web;


import com.example.restaurant.dto.ReserveAndCount;
import com.example.restaurant.entity.Customer;
import com.example.restaurant.entity.CustomerCount;
import com.example.restaurant.entity.Reserve;
import com.example.restaurant.repository.CustomerRepository;
import com.example.restaurant.service.CustomerService;
import com.example.restaurant.service.ReserveService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import java.util.List;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReserveController {


  CustomerRepository customerRepository;
  CustomerService customerService;

  ReserveService reserveService;

  @GetMapping("/payment")
  @ResponseBody
  public ResponseEntity<Optional<Customer>> reserve(HttpSession session) {
    return ResponseEntity.ok(customerService.selectMember(session));
  }


  @PostMapping("/payment")
  public ResponseEntity<String> addReserve(@RequestBody ReserveAndCount reserveAndCount, HttpSession session) {
    String customerCounts = reserveService.addReserveAndCount(reserveAndCount,session);
    return ResponseEntity.ok(customerCounts);
  }







}