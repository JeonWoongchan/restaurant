package com.example.restaurant.service;


import com.example.restaurant.dto.GReserveDTO;
import com.example.restaurant.entity.GReserve;
import com.example.restaurant.entity.Guest;
import com.example.restaurant.repository.GreserveRepository;
import com.example.restaurant.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GuestService {

  @Autowired
  private GreserveRepository gReserveRepository;

  @Autowired
  private GuestRepository guestRepository;



  public Page<GReserveDTO> getGuestsByGuestId( int pageNumber, int pageSize,String phone) {


    Optional<Long> guestId = guestRepository.findByID(phone);

    if (guestId.isPresent()) {
      Long id = guestId.get(); // 값이 존재한다고 확신할 때 사용
      Pageable pageable = PageRequest.of(pageNumber, pageSize);

      Page<GReserveDTO> guestsPage = gReserveRepository.info(id,pageable);

      return guestsPage;

    } else {
      return  null;
    }
  }
}
