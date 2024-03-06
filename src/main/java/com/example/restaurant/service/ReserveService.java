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
      reserve.setCustomer(customer);
      String reserveDate = addLeadingZeroIfNeeded(reserve.getReserve_date());
      reserve.setReserve_date(reserveDate);
      reserve.setReg_date(formatDateTime(LocalDateTime.now()));
      reserve.setEnd_date(calculateEndDateTime(reserve.getReserve_date()));
      reserveRepository.save(reserve);
      return "회원 등록 완료";
    } else {

      return "비회원 등록 완료";
    }
  }

  private String formatDateTime(LocalDateTime dateTime) {
    return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
  }

  private String calculateEndDateTime(String reserveDate) {
    LocalDateTime LdateTime = LocalDateTime.parse(reserveDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    return formatDateTime(LdateTime.plusHours(8)); // 8시간을 더한 새로운 날짜와 시간
  }

  private String addLeadingZeroIfNeeded(String date) {
    if (date.length() == 10 && date.charAt(8) == '-') {
      return date.substring(0, 8) + "0" + date.charAt(9);
    }
    return date;
  }

}
