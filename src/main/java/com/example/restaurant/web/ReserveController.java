package com.example.restaurant.web;

import com.example.restaurant.dto.CustomerDto;
import com.example.restaurant.dto.ReserveAndGuestDTO;

import com.example.restaurant.repository.CustomerRepository;
import com.example.restaurant.service.CapacityService;
import com.example.restaurant.service.CustomerService;
import com.example.restaurant.service.ReserveService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Map;
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

  @Autowired
  CapacityService capacityService;

  @PostMapping("/user-data")
  @ResponseBody
  public ResponseEntity<Optional<CustomerDto>> reserve(HttpSession session) {
    return ResponseEntity.ok(customerService.selectMember(session));
  }


  @PostMapping("/payment")
  public ResponseEntity<HashMap<String,Integer>> addreserveController(HttpSession session, @RequestBody ReserveAndGuestDTO dto) {
    return ResponseEntity.ok(reserveService.addReserve(session, dto));
  }

  @PostMapping("/time")
  public ResponseEntity<Map<String,Integer>> selectreserve(HttpSession session, @RequestBody String date,@RequestBody int total_count) {
    return ResponseEntity.ok(capacityService.getAvailableSlots(date,total_count));
  }
}
