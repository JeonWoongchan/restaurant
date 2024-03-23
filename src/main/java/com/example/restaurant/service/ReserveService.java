package com.example.restaurant.service;

import com.example.restaurant.dto.ReserveDTO;
import com.example.restaurant.entity.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.restaurant.repository.GuestRepository;

import com.example.restaurant.repository.ReserveGusetRepository;
import com.example.restaurant.repository.ReserveRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

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
  GuestRepository guestRepository;

  public HashMap<String, Integer> addreserve(HttpSession session, ReserveDTO dto) {
    HashMap<String, Integer> save = new HashMap<>();

    Optional<Customer> optionalCustomer = customerService.findByIdMembmer(session);
    if (optionalCustomer.isPresent()) {

      Customer customer = optionalCustomer.get();
      dto.getReserve().setCustomer(customer);

      String reserveDate = addLeadingZeroIfNeeded(dto.getReserve().getReserve_date());
      dto.getReserve().setReserve_date(reserveDate);
      dto.getReserve().setReg_date(formatDateTime(LocalDateTime.now()));
      dto.getReserve().setEnd_date(calculateEndDateTime(dto.getReserve().getReserve_date()));

      reserveRepository.save(dto.getReserve());
      save.put("status", 1);
    } else {

      Long id = generateRandomLong(1,10000000);
      dto.getGuest().setGuest_id(id);
      String phone = dto.getGuest().getPhone();

      String filterphone = filterPhoneNumber(phone);
      dto.getGuest().setPhone(filterphone);
      Guest guest = new Guest(id,filterphone);

      guestRepository.save(dto.getGuest());


      dto.getGreserve().setGuest(guest);


      String reserveDate = addLeadingZeroIfNeeded(dto.getGreserve().getReserve_date());
      dto.getGreserve().setReserve_date(reserveDate);
      dto.getGreserve().setReg_date(formatDateTime(LocalDateTime.now()));
      dto.getGreserve().setEnd_date(calculateEndDateTime(dto.getGreserve().getReserve_date()));


      reservegusetRepository.save(dto.getGreserve());

      save.put("status", 2);
    }
    return save;
  }


  // 기존 코드 생략



  // 날짜 형식 메소드
  private String formatDateTime(LocalDateTime dateTime) {
    return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
  }

  // END date 정하는 메소드
  private String calculateEndDateTime(String reserveDate) {
    LocalDateTime LdateTime = LocalDateTime.parse(reserveDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    return formatDateTime(LdateTime.plusHours(8)); // 8시간을 더한 새로운 날짜와 시간
  }

  // 날짜 month 에서 0누락되는거 추가하는 메소드
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

  
  // 번호 메소드
  public static String filterPhoneNumber(String phoneNumber) {
    // 정규 표현식 패턴 설정 (숫자 이외의 문자 모두)
    Pattern pattern = Pattern.compile("[^0-9]");

    // 정규 표현식 패턴과 매치되는 부분 찾기
    Matcher matcher = pattern.matcher(phoneNumber);

    // 매치된 부분을 제거하여 숫자만 남기기
    String filteredPhoneNumber = matcher.replaceAll("");

    return filteredPhoneNumber;
  }


}
