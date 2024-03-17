package com.example.restaurant.web;

import com.example.restaurant.entity.*;

import com.example.restaurant.repository.CustomerRepository;
import com.example.restaurant.service.CustomerService;
import com.example.restaurant.service.ReserveService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
  @Autowired
  CustomerService customerService;

  @Autowired
  ReserveService reserveService;

  @PostMapping("/user-data")
  @ResponseBody
  public ResponseEntity<Optional<Customer>> reserve(HttpSession session) {
    return ResponseEntity.ok(customerService.selectMember(session));
  }

  @PostMapping("/payment")
  public ResponseEntity<HashMap<String,Integer>> addreserveController(HttpSession session, @RequestBody ReservationDTO reservationDTO) {
    return ResponseEntity.ok(reserveService.addreserve(session, reservationDTO));
  }
}
