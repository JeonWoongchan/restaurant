package com.example.restaurant.service;

import com.example.restaurant.dto.GReserveDTO;
import com.example.restaurant.dto.ReserveAndGuestDTO;
import com.example.restaurant.dto.ReserveDTO;
import com.example.restaurant.entity.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.example.restaurant.repository.CustomerRepository;
import com.example.restaurant.repository.GreserveRepository;
import com.example.restaurant.repository.GuestRepository;

import com.example.restaurant.repository.ReserveRepository;
import com.example.restaurant.repository.mybatis.GreserveMapper;
import com.example.restaurant.tool.ReservationUtils;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;

import java.util.HashMap;

import java.util.Objects;
import java.util.Optional;


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

  @Autowired
  GreserveMapper greserveMapper;


  // 예약 생성 메서드
  public HashMap<String, Integer> addReserve(HttpSession session, ReserveAndGuestDTO dto) {
    HashMap<String, Integer> save = new HashMap<>();
    Optional<Customer> optionalCustomer = customerService.findByIdMembmer(session);
    if (optionalCustomer.isPresent()) {
      if (isReservationAvailable(optionalCustomer.get())) {
        return  processCustomerReservation(dto, optionalCustomer.get());

      } else {
        save.put("status", -1);
      }
    } else {
      processGuestReservation(dto);
    }
    return save;
  }

  private boolean isReservationAvailable(Customer customer) {
    LocalDate timenow = ReservationUtils.convertToDatetime(ReservationUtils.formatDate(LocalDateTime.now()));
    List<ReserveDTO> reserveDTOS = reserveRepository.selectReserve(customer.getId());
    for (ReserveDTO reserveDTO : reserveDTOS) {
      LocalDate enddate = ReservationUtils.convertToDatetime(reserveDTO.getEnd_date());
      if (timenow.isBefore(enddate)) {
        return false;
      }
    }
    return true;
  }


  // 회원 예약 설정
  private HashMap <String,Integer> processCustomerReservation(ReserveAndGuestDTO dto, Customer customer) {
    HashMap <String,Integer> save = new HashMap<String,Integer>()
    dto.getReserve().setCustomer(customer);
    if (prepareReserveDetails(dto.getReserve())) {
      reserveRepository.save(dto.getReserve());
      save.put("status", 1);
    } else {
      save.put("status", -11);
    }
    return  save;
  }

  // db  id 랜덤 생성 중복 방지
  private Long generateUniqueGuestId() {
    Long newId;
    boolean isDuplicate = true;
    do {
      newId = ReservationUtils.generateRandomLong(1, 10000000);
      isDuplicate = guestRepository.existsById(newId);
    } while (isDuplicate);
    return newId;
  }


  private HashMap<String, Integer> processGuestReservation(ReserveAndGuestDTO dto) {
    HashMap<String, Integer> save = new HashMap<>();
    String phone = dto.getGuest().getPhone();
    Optional<Guest> phoneparse = guestRepository.findByID(phone);
    Guest guest;

    if (phoneparse.isPresent()) {
      guest = phoneparse.get();
      if (isFutureReservationExistsForGuest(guest)) {
        save.put("status", -1);
      } else {
        prepareAndSaveGReserve(dto, guest);
        save.put("status", 1);
      }
    } else {
      guest = createAndSaveGuest(dto.getGuest());
      prepareAndSaveGReserve(dto, guest);
      save.put("status", 1);
    }

    return save;
  }

  private boolean isFutureReservationExistsForGuest(Guest guest) {
    LocalDate timenow = ReservationUtils.convertToDatetime(ReservationUtils.formatDate(LocalDateTime.now()));
    List<GReserveDTO> greserveDTOS = greserveRepository.info(guest.getGuest_id());
    for (GReserveDTO greserveDTO : greserveDTOS) {
      LocalDate enddate = ReservationUtils.convertToDatetime(greserveDTO.getEnd_date());
      if (timenow.isBefore(enddate)) {
        return true;
      }
    }
    return false;
  }

  private void prepareAndSaveGReserve(ReserveAndGuestDTO dto, Guest guest) {
    prepareGReserveTimeDetails(dto.getGreserve());
    dto.getGreserve().setGuest(guest);
    greserveRepository.save(dto.getGreserve());
  }

  private Guest createAndSaveGuest(Guest guestInfo) {
    guestInfo.setGuest_id(generateUniqueGuestId());
    guestInfo.setPhone(ReservationUtils.filterPhoneNumber(guestInfo.getPhone()));
    guestRepository.save(guestInfo);
    return guestInfo;
  }

  private Boolean prepareReserveDetails(Reserve reserve) {
    String reserve_date = ReservationUtils.addLeadingZeroIfNeeded(reserve.getReserve_date());
    Integer getcount = greserveMapper.getcount(reserve_date);
    String  extractingTime = ReservationUtils.extractTimeFromString(reserve_date);
    HashMap<String,Integer> capacity = capacityGreserve();

    Integer capacityValue = capacity.get(extractingTime);

    Integer nowcount = reserve.getAdults_count() + reserve.getChildren_count() + reserve.getInfants_count() ;

    if ( capacityValue <  nowcount + getcount ) {
      return false;
    } else {
      reserve.setReserve_date(reserve_date);
      reserve.setReg_date(ReservationUtils.formatDateTime(LocalDateTime.now()));
      reserve.setEnd_date(ReservationUtils.calculateEndDateTime(reserve.getReserve_date()));
      return true;
    }

  }

  private Boolean prepareGReserveTimeDetails(GReserve gReserve) {
    String reserve_date = ReservationUtils.addLeadingZeroIfNeeded(gReserve.getReserve_date());

    Integer getcount = greserveMapper.getcount(reserve_date);
    String  extractingTime = ReservationUtils.extractTimeFromString(reserve_date);
    HashMap<String,Integer> capacity = capacityGreserve();

    Integer capacityValue = capacity.get(extractingTime);

    Integer nowcount = gReserve.getAdults_count() + gReserve.getChildren_count() + gReserve.getInfants_count() ;

    if ( capacityValue <  nowcount + getcount ) {
      return false;
    } else {
      gReserve.setReserve_date(reserve_date);

      System.out.println(ReservationUtils.formatDateTime(LocalDateTime.now()));
      gReserve.setReg_date(ReservationUtils.formatDateTime(LocalDateTime.now()));
      gReserve.setEnd_date(ReservationUtils.calculateEndDateTime(gReserve.getReserve_date()));
      return true;
    }

  }

  public List<ReserveDTO> selectReserve(HttpSession session) {


    Optional<Customer> id = customerService.findByIdMembmer(session);


    if (id.isPresent()) {
      return reserveRepository.selectReserve(id.get().getId());
    } else {
      return null;
    }

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






  public  HashMap<String,Integer> capacityGreserve() {

    HashMap<String, Integer> capacity = new HashMap<String, Integer>();

    // 시간별 인원 추가
    String[] hours = {"11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
    Integer[] capacities = {20, 30, 50, 40, 25, 75, 35, 32, 40, 40};

    // 시간별 인원 일괄 추가
    for (int i = 0; i < hours.length; i++) {
      capacity.put(hours[i], capacities[i]);
    }
    return  capacity;
  }


}
