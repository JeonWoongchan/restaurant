package com.example.restaurant.service;


import com.example.restaurant.dto.GReserveDTO;
import com.example.restaurant.entity.Guest;
import com.example.restaurant.repository.GreserveRepository;
import com.example.restaurant.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuestService {
  @Autowired
  private GuestRepository guestRepository;

  @Autowired
  private GreserveRepository greserveRepository;



  public List<GReserveDTO> selectinfo(String phone, Guest guest) {




    Optional<Guest> guestid = guestRepository.findByID(phone);




    if (guestid.isPresent()) {
      Guest guests = guestid.get();
      // Guest 개체를 생성하여 값을 설정합니다.
      Guest newGuest = new Guest(guest.getGuest_id(), phone);


      List<GReserveDTO> guestsPage = greserveRepository.info(newGuest);
      return guestsPage;

    } else {
      return null;
    }


  }
}
