package com.example.restaurant.web;

import com.example.restaurant.repository.GuestRepository;
import com.example.restaurant.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.restaurant.entity.Guest;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class GuestController {

  @Autowired
  private GuestService guestService;

  @Autowired
  private GuestRepository guestRepository;
  @GetMapping("/guests/{guestId}")
  public ResponseEntity<Page<Guest>> getGuestsByGuestId(@RequestBody Guest guest,
                                                        @RequestParam(value = "page", defaultValue = "0") int page,
                                                        @RequestParam(value = "size", defaultValue = "10") int size) {

    String phone = guest.getPhone();


    Optional<Long> GuestID = guestRepository.findByID(phone);

    if (GuestID.isPresent()) {
      Long id = GuestID.get(); // 값이 존재한다고 확신할 때 사용



      Page<Guest> guestsPage = guestService.getGuestsByGuestId(id, page, size);

    } else {

    }





    // 성공적으로 데이터를 조회했을 때 HTTP 상태 코드 200(OK)와 함께 데이터를 반환
    return ResponseEntity.ok().body(""d"");
  }
}