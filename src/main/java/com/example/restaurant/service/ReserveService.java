package com.example.restaurant.service;



import com.example.restaurant.entity.Customer;

import com.example.restaurant.entity.Guest;
import com.example.restaurant.entity.Reserve;
import com.example.restaurant.repository.CustomerRepository;
import com.example.restaurant.repository.GusetRepository;
import com.example.restaurant.repository.ReserveRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReserveService {


  ReserveRepository reserveRepository;
  CustomerRepository customerRepository;

  CustomerService customerService;


  GusetRepository gusetRepository;




  public String  addreserve(Reserve reserve,  Guest guest, HttpSession session) {

    Customer id = customerService.findByIdMembmer(session);


    if (id != null) {

      reserve.setCustomer(id);
      reserveRepository.save(reserve);
      return "회원등록완료";
    } else {
      gusetRepository.save(guest);
      return "비회원등록완료";
    }

  }









}
