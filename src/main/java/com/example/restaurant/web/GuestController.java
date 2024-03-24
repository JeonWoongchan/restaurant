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
  @PostMapping("/my-page/reservGuest")
  public ResponseEntity<List<GReserveDTO>> getGuestsByGuestId(@RequestBody Guest guest) {
    String phone = guest.getPhone();
    List<GReserveDTO> guests = guestService.selectinfo(phone);
    return new ResponseEntity<>(guests, HttpStatus.OK);
  }
}