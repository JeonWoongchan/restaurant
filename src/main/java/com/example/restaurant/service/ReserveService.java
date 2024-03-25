package com.example.restaurant.service;

import com.example.restaurant.dto.ReserveAndGuestDTO;
import com.example.restaurant.dto.ReserveDTO;
import com.example.restaurant.entity.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.restaurant.repository.CustomerRepository;
import com.example.restaurant.repository.GreserveRepository;
import com.example.restaurant.repository.GuestRepository;

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
  GreserveRepository greserveRepository;

  @Autowired
  CustomerService customerService;

  @Autowired
  CustomerRepository customerRepository;

  @Autowired
  GuestRepository guestRepository;

  public HashMap<String, Integer> addReserve(HttpSession session, ReserveAndGuestDTO dto) {
    HashMap<String, Integer> save = new HashMap<>();
    Optional<Customer> optionalCustomer = customerService.findByIdMembmer(session);








    if (optionalCustomer.isPresent()) {
      processCustomerReservation(dto, optionalCustomer.get());
      save.put("status", 1);
    } else {
      processGuestReservation(dto);
      save.put("status", 2);
    }
    return save;
  }

  private void processCustomerReservation(ReserveAndGuestDTO dto, Customer customer) {
    dto.getReserve().setCustomer(customer);
    prepareReserveDetails(dto.getReserve());
    reserveRepository.save(dto.getReserve());
  }

  private Long generateUniqueGuestId() {
    Long newId;
    boolean isDuplicate = true;
    do {
      newId = generateRandomLong(1, 10000000);
      isDuplicate = guestRepository.existsById(newId);
    } while (isDuplicate);
    return newId;
  }


  private void processGuestReservation(ReserveAndGuestDTO dto) {
    String phone = dto.getGuest().getPhone();
    Optional<Guest> phoneparse = guestRepository.findByID(phone);
    Guest guest;


    if (phoneparse.isPresent()) {
      guest = phoneparse.get();
      prepareGReserveDetails(dto.getGreserve());
    } else {
      guest = createAndSaveGuest(dto.getGuest());
      prepareGReserveDetails(dto.getGreserve());
    }

    dto.getGreserve().setGuest(guest);
    greserveRepository.save(dto.getGreserve());
  }

  private Guest createAndSaveGuest(Guest guestInfo) {
    guestInfo.setGuest_id(generateUniqueGuestId());
    guestInfo.setPhone(filterPhoneNumber(guestInfo.getPhone()));
    guestRepository.save(guestInfo);
    return guestInfo;
  }

  private void prepareReserveDetails(Reserve reserve) {
    String reserveDate = addLeadingZeroIfNeeded(reserve.getReserve_date());
    reserve.setReserve_date(reserveDate);
    reserve.setReg_date(formatDateTime(LocalDateTime.now()));
    reserve.setEnd_date(calculateEndDateTime(reserve.getReserve_date()));
  }

  private void prepareGReserveDetails(GReserve gReserve) {
    String reserveDate = addLeadingZeroIfNeeded(gReserve.getReserve_date());
    gReserve.setReserve_date(reserveDate);

    System.out.println(formatDateTime(LocalDateTime.now()));
    gReserve.setReg_date(formatDateTime(LocalDateTime.now()));
    gReserve.setEnd_date(calculateEndDateTime(gReserve.getReserve_date()));
  }

  public List<ReserveDTO> selectReserve(HttpSession session) {


    Optional<Customer> id = customerService.findByIdMembmer(session);


    Long customer_id = id.get().getId();


    return reserveRepository.selectReserve(customer_id);


  }

  public List<ReserveDTO> selectrinfo(HttpSession session) {


    Optional<Customer> customerOptional = customerService.selectMember2(session);

    if (customerOptional.isPresent()) {

      Long id = customerOptional.get().getId();


      List<ReserveDTO> customerPage = reserveRepository.selectReserve(id);
      return customerPage;
    } else {
      return null;
    }


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
