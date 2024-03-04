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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReserveService {

  @Autowired
  ReserveRepository reserveRepository;


  @Autowired
  CustomerService customerService;







  public String addreserve(Reserve reserve, HttpSession session) {

    Optional<Customer> optionalCustomer = customerService.findByIdMembmer(session);

    if (optionalCustomer.isPresent()) {
      Customer customer = optionalCustomer.get();


      LocalDateTime now = LocalDateTime.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
      reserve.setCustomer(customer);
      String nowtime = now.format(formatter);



      reserve.setReg_date(nowtime);

      String reservedate = reserve.getReserve_date();


      LocalDateTime LdateTime = LocalDateTime.parse(reservedate, formatter);

      LocalDateTime newDateTime = LdateTime.plusHours(8); // 8시간을 더한 새로운 날짜와 시간

      String end_date = newDateTime.format(formatter); // 문자열로 변환

      reserve.setEnd_date(end_date);


      reserveRepository.save(reserve);
      return "회원 등록 완료";
    } else {
      // 비회원 처리 로직
      return "비회원 등록 완료";
    }
  }












}
