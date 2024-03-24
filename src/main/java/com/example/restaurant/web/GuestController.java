package com.example.restaurant.web;

import com.example.restaurant.dto.GReserveDTO;
import com.example.restaurant.repository.GreserveRepository;
import com.example.restaurant.repository.GuestRepository;
import com.example.restaurant.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.restaurant.entity.Guest;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class GuestController {

  @Autowired
  private GuestService guestService;

  @Autowired
  private GuestRepository guestRepository;
  @Autowired
  private GreserveRepository gReserveRepository;
  @GetMapping("/guests")
  public ResponseEntity<Page<GReserveDTO>> getGuestsByGuestId(@RequestBody Guest guest,
                                                        @RequestParam(value = "page", defaultValue = "0") int page,
                                                        @RequestParam(value = "size", defaultValue = "10") int size) {

    String phone = guest.getPhone();

    Page<GReserveDTO> guests = guestService.getGuestsByGuestId(page, size, phone);
    return new ResponseEntity<>(guests, HttpStatus.OK);







    // 성공적으로 데이터를 조회했을 때 HTTP 상태 코드 200(OK)와 함께 데이터를 반환
  }
}