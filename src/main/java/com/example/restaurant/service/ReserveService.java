package com.example.restaurant.service;

import com.example.restaurant.dto.GReserveDTO;
import com.example.restaurant.dto.ReserveAndGuestDTO;
import com.example.restaurant.dto.ReserveDTO;
import com.example.restaurant.entity.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;


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
        return processCustomerReservation(dto, optionalCustomer.get());

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
    List<ReserveDTO> reserveDTOS = reserveRepository.selectReserve(customer.getCustomer_id());
    for (ReserveDTO reserveDTO : reserveDTOS) {
      LocalDate enddate = ReservationUtils.convertToDatetime(reserveDTO.getEnd_date());
      if (timenow.isBefore(enddate)) {
        return false;
      }
    }
    return true;
  }


  // 회원 예약 설정
  private HashMap<String, Integer> processCustomerReservation(ReserveAndGuestDTO dto, Customer customer) {
    HashMap<String, Integer> save = new HashMap<String, Integer>();
    dto.getReserve().setCustomer(customer);
    if (prepareReserveDetails(dto.getReserve())) {
      reserveRepository.save(dto.getReserve());
      save.put("status", 1);
    } else {
      save.put("status", -11);
    }
    return save;
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


    reserve.setReserve_date(reserve_date);
    reserve.setReg_date(ReservationUtils.formatDateTime(LocalDateTime.now()));
    reserve.setEnd_date(ReservationUtils.calculateEndDateTime(reserve.getReserve_date()));
    return true;


  }

  private Boolean prepareGReserveTimeDetails(GReserve gReserve) {
    String reserve_date = ReservationUtils.addLeadingZeroIfNeeded(gReserve.getReserve_date());


    gReserve.setReserve_date(reserve_date);

    System.out.println(ReservationUtils.formatDateTime(LocalDateTime.now()));
    gReserve.setReg_date(ReservationUtils.formatDateTime(LocalDateTime.now()));
    gReserve.setEnd_date(ReservationUtils.calculateEndDateTime(gReserve.getReserve_date()));
    return true;


  }

  public ResponseEntity<Map<String,Object>> selectReserve(HttpSession session, Long reserve_id) {
    // 세션에서 사용자의 ID를 찾습니다.

    Optional<Customer> customerOptional = customerService.findByIdMembmer(session);
    Map<String, Object> response = new HashMap<>();
    // 고객 정보가 존재하는지 확인합니다.
    if (customerOptional.isPresent()) {
      Long customerId = customerOptional.get().getCustomer_id();

      // 예약 정보를 찾습니다.
      Optional<ReserveDTO> reserveDTOOptional = reserveRepository.selectReserveandreserveid(customerId);

      // 예약 정보가 존재하면 해당 정보를 반환합니다.
      if (reserveDTOOptional.isPresent()) {
        response.put("status", 1);
        response.put("reserveDTO", reserveDTOOptional.get());

        return ResponseEntity.ok(response);
      } else {
        // 예약 정보가 존재하지 않는 경우: 상태 코드 -1을 포함한 응답 본문을 반환합니다.

        response.put("status", -1);
        return ResponseEntity.ok(response);
      }
    } else {
      // 고객 정보가 존재하지 않는 경우: 상태 코드 -2를 포함한 응답 본문을 반환합니다.

      response.put("status", -2);
      return ResponseEntity.ok(response);
    }
  }

  public ResponseEntity<Map<String, Object>> selectLongListReserve(HttpSession session) {
    // 응답 맵 객체를 생성합니다.
    Map<String, Object> response = new HashMap<>();

    // id를 가져옵니다.
    Optional<Customer> id = customerService.findByIdMembmer(session);

    if (id.isPresent()) {
      // customer_id를 가져옵니다.
      Long customerId = id.get().getCustomer_id();

      // reserveIds 리스트를 가져옵니다.
      List<Long> reserveIds = reserveRepository.SelectreserveId(customerId);

      // reserveIds 리스트가 비어있는지 확인합니다.
      if (reserveIds.isEmpty()) {
        // reserveIds 리스트가 비어있다면 "stat" : -2를 반환합니다.
        response.put("status", -2);
      } else {
        // reserveIds 리스트가 비어있지 않다면 각 요소를 맵에 추가합니다.
        List<Long> reserveIdList = new ArrayList<>();
        for (Long reserveId : reserveIds) {
          reserveIdList.add(reserveId);
        }
        // reserve_id 배열을 response에 추가합니다.
        response.put("reserve_id", reserveIdList);
        // reserveIds 리스트의 크기를 "size"로 추가합니다.

        // "status"를 1로 반환합니다.

        response.put("size", reserveIds.size());
        response.put("status", 1);
      }
    } else {
      // id가 존재하지 않는 경우 "stat" : -1을 반환합니다.
      response.put("status", -1);
    }

    // 생성된 응답 맵을 HTTP 상태 코드와 함께 반환합니다.
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  public List<ReserveDTO> selectrinfo(HttpSession session) {


    Optional<Customer> customerOptional = customerService.selectMember2(session);

    if (customerOptional.isPresent()) {
      Long id = customerOptional.get().getCustomer_id();

      List<ReserveDTO> customerPage = reserveRepository.selectReserve(id);
      return customerPage;
    } else {
      return null;
    }
  }


  public HashMap<String, Object> deleteReserve(Long reserve_id) {
    HashMap<String, Object> stat = new HashMap<String, Object>();
    System.out.println("reserve_id->" + reserve_id);
    Optional<Integer> selectdelete = reserveRepository.selectdelete(reserve_id);


    if (selectdelete.isPresent()) {
      int deleteisreserveid = reserveRepository.deleteisreserveid(reserve_id);

      if (deleteisreserveid >= 1) {
        System.out.println(deleteisreserveid);
        stat.put("success", 1);
      } else {
        stat.put("success", 2);
      }


    } else {
      stat.put("success", -1);
    }

    return stat;
  }


}
