package com.example.restaurant.web;

import com.example.restaurant.dto.GReserveDTO;
import com.example.restaurant.dto.ReserveDTO;
import com.example.restaurant.entity.Reserve;
import com.example.restaurant.repository.GreserveRepository;
import com.example.restaurant.repository.GuestRepository;
import com.example.restaurant.service.CustomerService;
import com.example.restaurant.service.GuestService;
import com.example.restaurant.service.ReserveService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.restaurant.entity.Guest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/my-page")
public class GuestController {

  @Autowired
  private GuestService guestService;

  @Autowired
  private GuestRepository guestRepository;
  @Autowired
  private GreserveRepository gReserveRepository;

  @Autowired
  private ReserveService reserveService;


  //비회원 예약 조회
  @PostMapping("/reservGuest")
  public ResponseEntity<Page<GReserveDTO>>getGuestsByGuestId(@RequestBody Guest guest,@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size) {
    String phone = guest.getPhone();
    Page<GReserveDTO> selectinfo = guestService.selectinfo(phone,page,size);
    return new ResponseEntity<>(selectinfo, HttpStatus.OK);
  }


  //예약 조회
  @PostMapping("/reserve")
  public ResponseEntity<List<ReserveDTO>> reserveController(HttpSession session) {

    List<ReserveDTO> customers = reserveService.selectReserve(session);
    return new ResponseEntity<>(customers, HttpStatus.OK);
  }
}