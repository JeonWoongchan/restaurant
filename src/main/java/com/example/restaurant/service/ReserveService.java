package com.example.restaurant.service;

import com.example.restaurant.entity.*;

import com.example.restaurant.repository.CustomerRepository;
import com.example.restaurant.repository.GusetRepository;
import com.example.restaurant.repository.ReserveGusetRepository;
import com.example.restaurant.repository.ReserveRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReserveService {

  @Autowired
  ReserveRepository reserveRepository;

  @Autowired
  ReserveGusetRepository reservegusetRepository;
  @Autowired
  CustomerService customerService;

  @Autowired
  GusetRepository gusetRepository;

  public HashMap<String,Integer> addreserve(HttpSession session, ReservationDTO reservationDTO) {

    HashMap<String,Integer> save = new HashMap<>();
    Reserve reserve = reservationDTO.getReserve();
    Guest guest = reservationDTO.getGuest();
    ReserveGuest reserveGuest = reservationDTO.getReserveGuest();

    Optional<Customer> optionalCustomer = customerService.findByIdMembmer(session);
    if (optionalCustomer.isPresent()) {
      Customer customer = optionalCustomer.get();
      reserve.setCustomer(customer);
      String reserveDate = addLeadingZeroIfNeeded(reserve.getReserve_date());
      reserve.setReserve_date(reserveDate);
      reserve.setReg_date(formatDateTime(LocalDateTime.now()));
      reserve.setEnd_date(calculateEndDateTime(reserve.getReserve_date()));
      reserveRepository.save(reserve);
      save.put("status",1);
      return save;

    } else {
      // 비회원의 경우 ReserveGuest 객체 설정


      // 1부터 999999(6자리) 사이의 랜덤 숫자 생성
      long randomNumber = generateRandomLong(1, 999999);

      guest.setGuest_id(randomNumber);



      gusetRepository.save(guest);


      reserveGuest.setGuest(guest);

      String reserveDate = addLeadingZeroIfNeeded(reserve.getReserve_date());
      reserveGuest.setReserve_date(reserveDate);
      reserveGuest.setReg_date(formatDateTime(LocalDateTime.now()));
      reserveGuest.setEnd_date(calculateEndDateTime(reserve.getReserve_date()));
      reservegusetRepository.save(reserveGuest); // 예시로 ReserveGuest를 저장하는 것으로 가정

      save.put("status",2);
      return save;
    }


  }

  // 기존 코드 생략




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

  private static long generateRandomLong(long min, long max) {

    Random random = new Random();
    if (min >= max) {
      throw new IllegalArgumentException("max must be greater than min");
    }

    // 범위 내의 랜덤 숫자 생성
    long range = max - min;
    long fraction = (long) (range * random.nextDouble());
    return fraction + min;
  }


}
