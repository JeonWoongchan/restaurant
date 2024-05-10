package com.example.restaurant.web;

import com.example.restaurant.dto.GReserveDTO;
import com.example.restaurant.dto.ReserveDTO;
import com.example.restaurant.repository.GreserveRepository;
import com.example.restaurant.repository.GuestRepository;
import com.example.restaurant.service.GuestService;
import com.example.restaurant.service.ReserveService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PublicController {

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
  public ResponseEntity<List<GReserveDTO>>getGuestsByGuestId(@RequestBody Guest guest,@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size) {
    String phone = guest.getPhone();
    List<GReserveDTO> selectinfo = guestService.selectinfos(phone);
    return new ResponseEntity<>(selectinfo, HttpStatus.OK);
  }

 
  //예약 조회
  @PostMapping("/reserve")
  public  ResponseEntity<Map<String,Object>> reserveController(HttpSession session,@RequestBody ReserveDTO reserveDTO) {

    System.out.println(reserveDTO.getReserve_id());
    return  reserveService.selectReserve(session,reserveDTO.getReserve_id());
  }
}