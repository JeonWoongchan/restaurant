package com.example.restaurant.service;


import com.example.restaurant.dto.GReserveDTO;
import com.example.restaurant.entity.Guest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.example.restaurant.repository.GreserveRepository;
import com.example.restaurant.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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



  public Page<GReserveDTO>  selectinfo(String phone,@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size) {
    Optional <Guest> guestOptional = guestRepository.findByID(phone);

    if (guestOptional.isPresent()) {

      Long id = guestOptional.get().getGuest_id();


      Pageable pageable = PageRequest.of(page, size);

      Page<GReserveDTO> guestsPage = greserveRepository.pageinfo(id, pageable);

      return guestsPage;

    } else {
      return null;
    }



  }
}
