package com.example.restaurant.service;


import com.example.restaurant.dto.GReserveDTO;
import com.example.restaurant.entity.Guest;
import com.example.restaurant.repository.GreserveRepository;
import com.example.restaurant.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GuestService {
  @Autowired
  private GuestRepository guestRepository;

  @Autowired
  private GreserveRepository greserveRepository;



  public List<GReserveDTO>  selectinfo(String phone) {
    Optional <Guest> guestOptional = guestRepository.findByID(phone);

    if (guestOptional.isPresent()) {

      Long id = guestOptional.get().getGuest_id();



      List<GReserveDTO> guestsPage = greserveRepository.info(id);

      return guestsPage;

    } else {
      return null;
    }



  }
}
